package com.example.katerynastorozh.usergenerator.util;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UserItem implements Serializable {

        private String gender;
        private Name name;
        private String email;
        private Dob dob;
        private Picture picture;
        private String phone;


        public String getFirstName() {
            return name.getFirst();
        }

        public String getLastName() {
            return name.getLast();
        }

        public String getFirstLastName() {
            return name.getFirst() + " " + name.getLast();
        }



        public String getGender() {
            return gender;
        }


        public String getDateOfBirth() {
            DateFormat formatter = new SimpleDateFormat("YYYY-mm-dd");

            Date date = null;
            try {
                date = formatter.parse(dob.getDate());
            } catch (ParseException e) {
                date = new Date();
            }
            String formatedDate = formatter.format(date);
            return formatedDate;
    }



    public String getPhoneNumber() {
        return phone;
    }

    public String getUrlThumbnail() {
        return picture.getThumbnail();
    }

    public String getUrlLarge()
    {
        return picture.getLarge();
    }


    public String getEmail() {
        return email;
    }


}
