import java.util.ArrayList;
public class Taskmanager {

private ArrayList<Task> tasks;

public Taskmanager()
{
    this.tasks=new ArrayList<>();
}
public void addTask(Task task)
{
    tasks.add(task);
}

public void removeTask(int index)
{
    tasks.remove(index);
}
public void sortTaskPriority()
{
    for (int i=0;i<tasks.size()-1;i++)
    {

    for (int j=i+1;j<tasks.size();j++)
    {
        if(tasks.get(i).getTaskPriority().equals("Optional") && tasks.get(j).getTaskPriority().equals("Mandatory"))
        {
            Task temp = tasks.get(i);
            tasks.set(i, tasks.get(j));
            tasks.set(j, temp);
        }
        {
            
        }
    }
}
}
public void sortTaskDue()
{
    for (int i=0;i<tasks.size()-1;i++)
    {
        for(int j=i+1;j<tasks.size();j++)
        {
            if (tasks.get(i).getTaskPriority().equals("Mandatory") && tasks.get(j).getTaskPriority().equals("Mandatory"))
             {
                    String date1=tasks.get(i).getTaskDue().substring(6,10);
                    String date2=tasks.get(j).getTaskDue().substring(6,10);
                    int year1=Integer.parseInt(date1);
                    int year2=Integer.parseInt(date2);
                    if (year1<year2)
                    {
                        Task temp=tasks.get(i);
                        tasks.set(i,tasks.get(j));
                        tasks.set(j,temp);
                    }
                    else if (year1==year2)
                    {
                        String date3=tasks.get(i).getTaskDue().substring(3,5);
                        String date4=tasks.get(j).getTaskDue().substring(3,5);
                        int month1=Integer.parseInt(date3);
                        int month2=Integer.parseInt(date4);
                        if (month1<month2)
                        {
                            Task temp=tasks.get(i);
                            tasks.set(i,tasks.get(j));
                            tasks.set(j,temp);
                        }
                        else if (month1==month2)
                        {
                            String date5=tasks.get(i).getTaskDue().substring(0,2);
                            String date6=tasks.get(j).getTaskDue().substring(0,2);
                            int day1=Integer.parseInt(date5);
                            int day2=Integer.parseInt(date6);
                            if (day1<day2)
                            {
                                Task temp=tasks.get(i);
                                tasks.set(i,tasks.get(j));
                                tasks.set(j,temp);
                            }
                        }
                    }
             }
             else
                {
                    if (tasks.get(i).getTaskPriority().equals("Optional") && tasks.get(j).getTaskPriority().equals("Optional"))
                    {String date1=tasks.get(i).getTaskDue().substring(6,10);
                    String date2=tasks.get(j).getTaskDue().substring(6,10);
                    int year1=Integer.parseInt(date1);
                    int year2=Integer.parseInt(date2);
                    if (year1<year2) 
                    {
                        Task temp=tasks.get(i);
                        tasks.set(i,tasks.get(j));
                        tasks.set(j,temp);
                    }
                    else if (year1==year2)
                    {
                        String date3=tasks.get(i).getTaskDue().substring(3,5);
                        String date4=tasks.get(j).getTaskDue().substring(3,5);
                        int month1=Integer.parseInt(date3);
                        int month2=Integer.parseInt(date4);
                        if (month1<month2)
                        {
                            Task temp=tasks.get(i);
                            tasks.set(i,tasks.get(j));
                            tasks.set(j,temp);
                        }
                        else if (month1==month2)
                        {
                            String date5=tasks.get(i).getTaskDue().substring(0,2);
                            String date6=tasks.get(j).getTaskDue().substring(0,2);
                            int day1=Integer.parseInt(date5);
                            int day2=Integer.parseInt(date6);
                            if (day1<day2)
                            {
                                Task temp=tasks.get(i);
                                tasks.set(i,tasks.get(j));
                                tasks.set(j,temp);
                            }
                        }
                    }
                    
                       
                    }
                }
        }
    }
}
public ArrayList<Task> getTasks()
{return tasks;
}
public void setTasks(ArrayList<Task> tasks)
{
    this.tasks=tasks;
}
public void clearTasks() {
    tasks.clear(); 
}
}