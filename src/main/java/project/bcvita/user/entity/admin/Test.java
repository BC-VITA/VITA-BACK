package project.bcvita.user.entity.admin;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.*;


    @Entity
    @Getter
    @Setter
    @NoArgsConstructor
    @Builder
    public class Test {
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Id
        private Long testNumber;

        private String testName;


        public Test(Long testNumber, String testName) {
            this.testNumber = testNumber;
            this.testName = testName;
        }
    }


