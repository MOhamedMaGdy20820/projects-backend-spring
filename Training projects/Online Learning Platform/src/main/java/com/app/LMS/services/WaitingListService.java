package com.app.LMS.services;

import com.app.LMS.dto.DtoContent;
import com.app.LMS.entity.Course;
import com.app.LMS.entity.Instructor;
import com.app.LMS.entity.NotificationEmail;
import com.app.LMS.entity.WaitingList;
import com.app.LMS.exceptions.CustomException;
import com.app.LMS.repository.WaitingListRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class WaitingListService {


    private final WaitingListRepository waitingListRepository;

    private final MailService mailService;
    private final InstructorService instructorService;
    private final CourseService courseService;


    public String save(WaitingList course , long id) {
        Instructor instructor = instructorService.findInstructorById(id);
            course.setInstructor(instructor);
            waitingListRepository.save(course);
            return "Successfully added waiting list ";

    }

    public List<WaitingList> getAll() {
        return waitingListRepository.findAll();
    }

    public WaitingList findById(long id) {
        return waitingListRepository.findById(id).orElseThrow(()-> new CustomException("Course not found"));
    }

    public String acceptCourse(long id) {

        WaitingList waitingList = findById(id);
            Instructor instructor = instructorService.findInstructorById(waitingList.getInstructor().getId());
                mailService.sendMail(new NotificationEmail
                        (
                                "Your course has been approved " + waitingList.getTitle() ,
                                instructor.getEmail()   ,
                                "\n\n\nDear Mr./Ms. "+ instructor.getFirstName()+"\n" +
                                        "\n" +
                                        "I hope this message finds you well.\n" +
                                        "\n" +
                                        "We are pleased to inform you that your course has been successfully approved on our platform. We truly appreciate your efforts and are excited to see your valuable contributions.\n" +
                                        "\n" +
                                        "Please proceed with preparing and uploading the course content at your earliest convenience. We recommend organizing the content into modules and lessons for easier student navigation.\n" +
                                        "\n" +
                                        "If you need any assistance or have any questions, please don’t hesitate to reach out.\n" +
                                        "\n" +
                                        "Thank you for your cooperation.\n\n\n")
                );

                Course course = new Course();

                course.setTitle(waitingList.getTitle());
                course.setDescription(waitingList.getDescription());
                course.setInstructor(instructor);
                course.setCategory(waitingList.getCategory());

                courseService.save(course);
                waitingListRepository.deleteById(id);

                return "The course has been added for students to participate in" + waitingList.getTitle() ;
    }

    public String rejectCourse(long id) {
        WaitingList waitingList = findById(id);
        Instructor instructor = instructorService.findInstructorById(waitingList.getInstructor().getId());
        mailService.sendMail(new NotificationEmail
            (
                    "Your course has been declined " + waitingList.getTitle() ,
                    instructor.getEmail()   ,
                    "\n\n\nDear Mr./Ms. "+ instructor.getFirstName()+"\n" +
                            "\n" +
                            "I hope you are doing well.\n" +
                            "\n" +
                            "After careful consideration, we regret to inform you that your course submission has not been approved for inclusion on our platform. This decision was made following a thorough review process, and we understand that this news may be disappointing.\n" +
                            "\n" +
                            "We encourage you to review the feedback provided by our review team, which will help you understand the areas that need improvement. If you wish to resubmit the course after making the necessary adjustments, we would be happy to reconsider it.\n" +
                            "\n" +
                            "If you have any questions or need further clarification, please feel free to reach out to us.\n" +
                            "\n" +
                            "Thank you for your understanding and cooperation.\n" +
                            "\n" +
                            "Best regards.")
            );
        waitingListRepository.deleteById(id);
        return "Course has been deleted";

    }


}
