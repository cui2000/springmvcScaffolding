package demo.dao.impl;

import org.springframework.stereotype.Repository;

import demo.bean.Payment;
import demo.dao.IPaymentDao;

@Repository("paymentDao")
public class PaymentDaoImpl extends BaseDaoImpl<Payment> implements IPaymentDao {

}