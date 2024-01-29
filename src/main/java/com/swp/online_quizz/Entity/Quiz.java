package com.swp.online_quizz.Entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Quizzes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Quiz {

    @Id
    @Column(name = "QuizID", nullable = false)
    private Integer quizId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TeacherID")
    private User teacher;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SubjectID")
    @JsonBackReference
    @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
    private Subject subject;

    @Column(name = "QuizName", length = 100)
    private String quizName;

    @Column(name = "TimeLimit")
    private Integer timeLimit;

    @Column(name = "IsCompleted")
    private Boolean isCompleted;

    @OneToMany(mappedBy = "quiz")
    @JsonManagedReference
    private List<Question> listQuestions;

    @OneToMany(mappedBy = "quiz")
    @JsonManagedReference
    private List<QuizAttempt> listQuizAttemps;

    // @OneToMany(mappedBy = "quiz")
    // @JsonManagedReference
    // @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    // private Set<QuizAttempt> listQuizAttemps;
    @OneToMany(mappedBy = "quiz")
    private List<QuizAttempt> quizAttempts;

}
