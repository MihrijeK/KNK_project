package sample.Models.View;

import java.util.Date;
public class PaymentModel {
        private int payment_id;
        private String firstname;
        private String lastname;
        private Date date;
        private double price;
        private int isPayed;
        
         public PaymentModel(){}

        public PaymentModel(int payment_id, String firstname,String lastname,Date date,double price,int isPayed){
            this.payment_id=payment_id;
            this.firstname=firstname;
            this.lastname=lastname;
            this.date=date;
            this.price=price;
            this.isPayed=isPayed;
        }

}
