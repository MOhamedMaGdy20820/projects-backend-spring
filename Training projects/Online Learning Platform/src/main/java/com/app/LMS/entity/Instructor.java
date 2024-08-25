package com.app.LMS.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Instructor extends BaseUser<Long> {


    @OneToMany(mappedBy = "instructor" , cascade = {CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST,CascadeType.REFRESH})
    private List<WaitingList> coursesWaitingList;

    @OneToMany(mappedBy = "instructor" , cascade = {CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST,CascadeType.REFRESH})
    private List<Course> courseList;


}
