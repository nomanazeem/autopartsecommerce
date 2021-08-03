package nazeem.autoparts.library.service;

import nazeem.autoparts.library.model.Customer;
import nazeem.autoparts.library.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    JavaMailSender javaMailSender;

    @Autowired
    private Environment environment;

    public boolean testEmail(){
        try {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setTo(environment.getProperty("to.email"));
            simpleMailMessage.setSubject("Testing email");
            simpleMailMessage.setText("Dear folk, is this testing email sent by Ecommerce application !");
            simpleMailMessage.setFrom(environment.getProperty("from.email"));

            javaMailSender.send(simpleMailMessage);

            return true;
        }catch (Exception ex){
            ex.printStackTrace();
            return false;
        }
    }

    public boolean registration(String appUrl, Customer customer){
        try {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setTo(customer.getUsername());//should be email address
            simpleMailMessage.setSubject("Auto parts - Registration");

            String loginUrl = appUrl+"/login";

            simpleMailMessage.setText(
                    String.format("Dear %s, Thank you for registration with Ecommerce Application !." +
                                    "\n please login %s"
                    , customer.getFullName(), loginUrl));
            simpleMailMessage.setFrom(environment.getProperty("from.email"));

            javaMailSender.send(simpleMailMessage);

            return true;
        }catch (Exception ex){
            ex.printStackTrace();
            return false;
        }
    }
    public boolean orderCreation(String appUrl, Order order){
        try {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setTo(order.getCustomer().getUsername());//should be email address
            simpleMailMessage.setSubject("Auto parts - Order Creation");

            String viewOrdersUrl = appUrl+"/order-history";


            simpleMailMessage.setText(
                    String.format("Dear %s, Thank you for made order with Ecommerce Application !"+
                                    "\n please view your order details %s"
                            , order.getCustomer().getFullName(), viewOrdersUrl));
            simpleMailMessage.setFrom(environment.getProperty("from.email"));

            javaMailSender.send(simpleMailMessage);

            return true;
        }catch (Exception ex){
            ex.printStackTrace();
            return false;
        }
    }

}
