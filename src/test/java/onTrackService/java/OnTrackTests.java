package onTrackService.java;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.text.SimpleDateFormat;
import java.util.*;
import onTrackService.java.OnTrackService.*;

public class OnTrackTests {

	private OnTrackService.TaskService taskService;
	private OnTrackService.FeedbackService feedbackService;
	private OnTrackService.TutoringService tutoringService;
	private OnTrackService.StudyGroupService studyGroupService;
	private OnTrackService.ProgressReportService progressReportService;


	@BeforeEach
	public void setup() {
		taskService = new OnTrackService.TaskService();
		feedbackService = new OnTrackService.FeedbackService();
		tutoringService = new OnTrackService.TutoringService();
		studyGroupService = new OnTrackService.StudyGroupService();
		progressReportService = new OnTrackService.ProgressReportService();
	}

	 // Task Collaboration Test Cases
	  
	  @Test
	  public void testCreateTask() { 
		  Task task = taskService.createTask("Collaborative Task", "This is a collaborative task.", "student1"); 
		  assertNotNull(task); assertEquals("Collaborative Task", task.getTitle()); 
		  assertEquals("This is a collaborative task.",task.getDescription()); }

	@Test
	public void testAddCollaborator() {
		Task task = taskService.createTask("Collaborative Task", "This is a collaborative task.", "student1");
		int taskId = task.getId();
		boolean success = taskService.addCollaborator(taskId, "student2");
		assertTrue(success);
		assertTrue(task.getCollaborators().contains("student2"));
	}

	// Interactive Feedback Test Cases
	@Test
	public void testProvideFeedback() {
		Feedback feedback = feedbackService.provideFeedback(1, "tutor1",
				"Great work! Consider revising the introduction.");
		assertNotNull(feedback);
		assertEquals("Great work! Consider revising the introduction.", feedback.getComments());
	}

	// Tutoring Sessions Test Cases
	@Test
	public void testScheduleSession() throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date sessionDate = sdf.parse("2024-05-10");

		Session session = tutoringService.scheduleSession("tutor1", "student1", sessionDate, "10:00 AM");
		assertNotNull(session);
		assertEquals("tutor1", session.getTutor());
		assertEquals("student1", session.getStudent());
	}

	// Study Groups Test Cases
	@Test
	public void testCreateStudyGroup() {
		StudyGroup group = studyGroupService.createStudyGroup("Math Study Group", "student1");
		assertNotNull(group);
		assertEquals("Math Study Group", group.getGroupName());
		assertEquals("student1", group.getCreator());
	}

	@Test
	public void testJoinStudyGroup() {
		StudyGroup group = studyGroupService.createStudyGroup("Math Study Group", "student1");
		int groupId = group.getId();
		boolean success = studyGroupService.joinStudyGroup("student2", groupId);
		assertTrue(success);
		assertTrue(group.getMembers().contains("student2"));
	}

	// Reports Test Cases
	@Test
	public void testGenerateReport() {
		ProgressReport report = progressReportService.generateReport("student1");
		assertNotNull(report);
		assertEquals("student1", report.getStudent());
		assertEquals(85, report.getAverageScore());
		assertEquals(20, report.getTasksCompleted());
	}

	@Test
	public void testFeedbackFormCreation() {
		FeedbackForm feedbackForm = new FeedbackForm("tutor1", "student1", 5, "Great tutor!");
		assertNotNull(feedbackForm);
	}

	@Test
	public void testGetTutor() {
		FeedbackForm feedbackForm = new FeedbackForm("tutor1", "student1", 5, "Great tutor!");
		assertEquals("tutor1", feedbackForm.getTutor());
	}

	@Test
	public void testGetStudent() {
		FeedbackForm feedbackForm = new FeedbackForm("tutor1", "student1", 5, "Great tutor!");
		assertEquals("student1", feedbackForm.getstudent());
	}

	@Test
	public void testGetRating() {
		FeedbackForm feedbackForm = new FeedbackForm("tutor1", "student1", 5, "Great tutor!");
		assertEquals(5, feedbackForm.getRating());
	}

	@Test
	public void testGetComments() {
		FeedbackForm feedbackForm = new FeedbackForm("tutor1", "student1", 5, "Great tutor!");
		assertEquals("Great tutor!", feedbackForm.getComments());
	}	

}
