import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class Task {
private String taskName;
private String taskDescription;
private String taskDue;
private String taskPriority;

public Task(String taskname,String taskdescription,String taskdue,boolean taskpriority){
    this.taskName=taskname;
    this.taskDescription="|"+taskdescription+"|";
    this.taskDue=LocalDate.parse(taskdue, DateTimeFormatter.ofPattern("dd/MM/yyyy")).format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    if (taskpriority){
        this.taskPriority="Mandatory";
    }
    else this.taskPriority="Optional";

}
public String getTaskName()
{
    return taskName;
}
public String getTaskDescription()
{
    return taskDescription;
}
public String getTaskDue()
{
    return taskDue;
}
public String getTaskPriority()
{
    return taskPriority;
}
public long daysUntilDue() {
        LocalDate currentDate = LocalDate.now();
        LocalDate dueDate = LocalDate.parse(taskDue, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        Period period = Period.between(currentDate, dueDate);
        
        return period.getYears() * 365 + period.getMonths() * 30 + period.getDays();
    }

}
