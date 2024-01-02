package com.backend.service;

import com.backend.common.BillVo;
import com.backend.common.IncomeExpense;
import com.backend.common.Rank;
import com.backend.entity.Bill;
import com.backend.mapper.BillMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

@Service
public class BillService extends ServiceImpl<BillMapper, Bill> {
    private final BillMapper billMapper;

    public BillService(BillMapper billMapper) {
        this.billMapper = billMapper;
    }

    public int create(Bill bill) {
        // 金额为0则不创建
        if (bill.getAmount() == null || bill.getAmount().compareTo(BigDecimal.ZERO) == 0)
            return 0;
        // 统一金额符号与收支类型
        if (bill.isType() ^ (bill.getAmount().compareTo(BigDecimal.ZERO) > 0))
            bill.setAmount(bill.getAmount().negate());
        // 账单的收支类型必须与分类匹配
        if (bill.isType() && (bill.getCategoryId() < 12 || bill.getCategoryId() > 16))
            return 0;
        if (!bill.isType() && (bill.getCategoryId() < 1 || bill.getCategoryId() > 11))
            return 0;
        // 如果没输入日期则默认为当天
        if (null == bill.getDate() || bill.getDate().isEmpty()) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            bill.setDate(simpleDateFormat.format(new Date()));
        } else {
            // 将日期格式化为"yyyy-MM-dd"
            String date = bill.getDate();
            if (Pattern.matches("^\\d{4}-\\d-\\d$", date)) {
                date = date.substring(0, 5) + '0' + date.substring(5, 7) + '0' + date.substring(7);
            } else if (Pattern.matches("^\\d{4}-\\d{2}-\\d$", date)) {
                date = date.substring(0, 8) + '0' + date.substring(8);
            } else if (Pattern.matches("^\\d{4}-\\d-\\d{2}$", date)) {
                date = date.substring(0, 5) + '0' + date.substring(5);
            } else {
                if (!Pattern.matches("^\\d{4}-\\d{2}-\\d{2}$", date)) {
                    return 0;
                }
            }
            // 判断日期是否合法
            LocalDate tmp;
            try {
                tmp = LocalDate.parse(date);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return 0;
            }
            int year = tmp.getYear();
            if (year < 1990 || year > 2050)
                return 0;
            bill.setDate(date);
        }
        bill.setId(null);
        return billMapper.insert(bill);
    }

    public List<BillVo> getList(Integer userId) {
        return billMapper.listById(userId);
    }

    public List<BillVo> listByYM(Integer userId, String year, @RequestParam String month) {
        // 格式化月份
        if (month.length() != 2)
            month = '0' + month;
        return billMapper.listByYM(userId, year + '-' + month + '%');
    }

    public IncomeExpense getYearSum(Integer userId, String year) {
        QueryWrapper<Bill> query = new QueryWrapper<>();
        // 对某年的收入进行求和
        query.select("sum(amount) as incomeSum");
        query.eq("user_id", userId);
        query.eq("type", true);
        query.likeRight("date", year);
        Bill bill1 = billMapper.selectOne(query); // 通过sum语句查询，只需要第一条结果
        query.clear();  // 清空查询语句
        // 对某年的支出进行求和
        query.select("sum(amount) as expenseSum");
        query.eq("user_id", userId);
        query.eq("type", false);
        query.likeRight("date", year);
        Bill bill2 = billMapper.selectOne(query);
        // 判断如果存在空查询，则该项结果需要手动置为0
        IncomeExpense result = new IncomeExpense();
        result.setIncome(bill1 == null ? BigDecimal.ZERO : bill1.getIncomeSum());
        result.setExpense(bill2 == null ? BigDecimal.ZERO : bill2.getExpenseSum());
        return result;
    }

    public List<IncomeExpense> getYearMonthSum(Integer userId, String year) {
        List<IncomeExpense> res = new ArrayList<>();
        QueryWrapper<Bill> query = new QueryWrapper<>();
        for (int i = 1; i <= 12; i++) {
            String yearMonth = year + "-";
            if (i < 10)
                yearMonth += "0" + i;
            else
                yearMonth += i;
            query.clear();
            query.select("sum(amount) as incomeSum");
            query.eq("user_id", userId);
            query.eq("type", true);
            query.likeRight("date", yearMonth);
            Bill bill1 = billMapper.selectOne(query); // 通过sum语句查询，只需要第一条结果
            query.clear();  // 清空查询语句
            query.select("sum(amount) as expenseSum");
            query.eq("user_id", userId);
            query.eq("type", false);
            query.likeRight("date", yearMonth);
            Bill bill2 = billMapper.selectOne(query);
            // 判断如果存在空查询，则该项结果需要手动置为0
            IncomeExpense result = new IncomeExpense();
            result.setIncome(bill1 == null ? BigDecimal.ZERO : bill1.getIncomeSum());
            result.setExpense(bill2 == null ? BigDecimal.ZERO : bill2.getExpenseSum());
            res.add(result);
        }
        return res;
    }

    public List<IncomeExpense> getThisWeek(Integer userId, Integer flag) {
        long currentTimeMillis = System.currentTimeMillis(); // 获取今日时间
        long dayMillis = 24 * 60 * 60 * 1000; // 一天的时间
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        day = day == 1 ? 7 : day - 1;
        long firstDayMillis = currentTimeMillis - (day - 1) * dayMillis - 7 * dayMillis * flag; // 获取周一的时间
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        QueryWrapper<Bill> query = new QueryWrapper<>();
        List<IncomeExpense> res = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            String tmp = dateFormat.format(firstDayMillis + i * dayMillis);
            query.clear();
            query.select("sum(amount) as incomeSum");
            query.eq("user_id", userId);
            query.eq("type", true);
            query.eq("date", tmp);
            Bill bill1 = billMapper.selectOne(query); // 通过sum语句查询，只需要第一条结果
            query.clear();  // 清空查询语句
            query.select("sum(amount) as expenseSum");
            query.eq("user_id", userId);
            query.eq("type", false);
            query.eq("date", tmp);
            Bill bill2 = billMapper.selectOne(query);
            // 判断如果存在空查询，则该项结果需要手动置为0
            IncomeExpense result = new IncomeExpense();
            result.setDate(tmp.substring(5));
            result.setIncome(bill1 == null ? BigDecimal.ZERO : bill1.getIncomeSum());
            result.setExpense(bill2 == null ? BigDecimal.ZERO : bill2.getExpenseSum());
            res.add(result);
        }
        return res;
    }

    public List<IncomeExpense> getThisMonth(Integer userId, Integer flag) {
        long currentTimeMillis = System.currentTimeMillis(); // 获取今日时间
        long dayMillis = 24 * 60 * 60 * 1000; // 一天的时间
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DATE);
        calendar.set(Calendar.DATE, 1);         // 将日期设为一号，
        calendar.add(Calendar.MONTH, 1);// 然后下一个月，
        calendar.add(Calendar.DATE, -1);// 的上一天就是月底，
        int totalDay = calendar.get(Calendar.DATE); // 获取这个月总共多少天
        long firstDayMillis = currentTimeMillis - (day - 1) * dayMillis; // 获取一号的时间
        for (int i = 0; i < flag; i++) {
            calendar.set(Calendar.DATE, 1);
            calendar.add(Calendar.DATE, -1);
            totalDay = calendar.get(Calendar.DATE);
            firstDayMillis -= totalDay * dayMillis; // 定位到上个月一号
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        QueryWrapper<Bill> query = new QueryWrapper<>();
        List<IncomeExpense> res = new ArrayList<>();
        for (int i = 0; i < totalDay; i++) {
            String tmp = dateFormat.format(firstDayMillis + i * dayMillis);
            query.clear();
            query.select("sum(amount) as incomeSum");
            query.eq("user_id", userId);
            query.eq("type", true);
            query.eq("date", tmp);
            Bill bill1 = billMapper.selectOne(query); // 通过sum语句查询，只需要第一条结果
            query.clear();  // 清空查询语句
            query.select("sum(amount) as expenseSum");
            query.eq("user_id", userId);
            query.eq("type", false);
            query.eq("date", tmp);
            Bill bill2 = billMapper.selectOne(query);
            // 判断如果存在空查询，则该项结果需要手动置为0
            IncomeExpense result = new IncomeExpense();
            result.setDate(String.valueOf(i + 1));
            result.setIncome(bill1 == null ? BigDecimal.ZERO : bill1.getIncomeSum());
            result.setExpense(bill2 == null ? BigDecimal.ZERO : bill2.getExpenseSum());
            res.add(result);
        }
        return res;
    }

    public List<IncomeExpense> getThisYear(Integer userId, Integer flag) {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        year -= flag;
        QueryWrapper<Bill> query = new QueryWrapper<>();
        List<IncomeExpense> res = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            String tmp = String.valueOf(year) + '-';
            if (i < 9)
                tmp += '0';
            tmp += String.valueOf(i + 1);
            query.clear();
            query.select("sum(amount) as incomeSum");
            query.eq("user_id", userId);
            query.eq("type", true);
            query.likeRight("date", tmp);
            Bill bill1 = billMapper.selectOne(query); // 通过sum语句查询，只需要第一条结果
            query.clear();  // 清空查询语句
            query.select("sum(amount) as expenseSum");
            query.eq("user_id", userId);
            query.eq("type", false);
            query.likeRight("date", tmp);
            Bill bill2 = billMapper.selectOne(query);
            // 判断如果存在空查询，则该项结果需要手动置为0
            IncomeExpense result = new IncomeExpense();
            result.setIncome(bill1 == null ? BigDecimal.ZERO : bill1.getIncomeSum());
            result.setExpense(bill2 == null ? BigDecimal.ZERO : bill2.getExpenseSum());
            res.add(result);
        }
        return res;
    }

    public List<Rank> getRank(Integer userId, String year, String month, boolean flag) {
        if (month.length() < 2)
            month = "0" + month;
        List<Rank> res = billMapper.rankMonth(userId, year + '-' + month, flag);
        BigDecimal total = BigDecimal.ZERO;
        for (Rank re : res) {
            total = total.add(re.getMoney());
        }
        for (Rank re : res) {
            re.setPercentage(re.getMoney().multiply(BigDecimal.valueOf(100)).divide(total, 2, RoundingMode.DOWN));
        }
        return res;
    }

    public BigDecimal getThisMonthExpense(Integer userId) {
        // 查询本月的支出
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM");
        String tmp = simpleDateFormat.format(new Date());
        QueryWrapper<Bill> query = new QueryWrapper<>();
        query.select("sum(amount) as expenseSum");
        query.eq("user_id", userId);
        query.eq("type", false);
        query.likeRight("date", tmp);
        Bill bill = billMapper.selectOne(query);
        if (bill == null) {
            return BigDecimal.ZERO;
        } else {
            return bill.getExpenseSum();
        }
    }
}
