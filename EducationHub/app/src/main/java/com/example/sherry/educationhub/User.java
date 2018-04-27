package com.example.sherry.educationhub;

public class User
    {
        private String username;
        private String grade;
        private String location;
        private String subject;
        private String password;

        public User(String username,
                    String grade,
                    String location, String subject,
                    String password){
            this.username=username;
            this.password=password;
            this.location=location;
            this.grade=grade;
            this.subject=subject;}

    }


