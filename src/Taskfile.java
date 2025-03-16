import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.io.IOException;//for ignorind the "notfoundexception"

public class Taskfile {
    private static final String Filename="tasks.txt";
    public static void saveTask(ArrayList<Task> tasks) throws IOException //for ignoring the "notfoundedexception"
    {try (FileWriter writer=new FileWriter(Filename)) 
        {int index=0;
            for (Task task:tasks)
            {   
                index++;
                writer.write(index + "^" + task.getTaskName() + "^" + task.getTaskDescription() + "^" + task.getTaskDue() + "^" + task.getTaskPriority() + "\n");
            }
        }
        System.out.println("Tasks saved to file");
        } 

        public static ArrayList<Task> readTask() throws IOException // for ignoring the "notfoundexception"
        {
            ArrayList<Task> tasks=new ArrayList<>();
            try (FileReader reader=new FileReader(Filename)) {
            int data;
            String taskname="";
            String taskdescription="";
            String taskdue="";
            boolean taskpriority=false;
            int index=0;
            while ((data=reader.read())!=-1)
            {
                char ch=(char)data;
                if (ch=='\n')
                {Task task=new Task(taskname,taskdescription,taskdue,taskpriority);
                tasks.add(task);
                 taskname="";
                 taskdescription="";
                 taskdue="";
                 taskpriority=false;
                 index=0;
                }
                else if (ch!='^' && index==1 && ch!='|')
                {taskname=taskname+ch;
                }
                else if (ch!='^' && index==2 && ch!='|')
                {taskdescription=taskdescription+ch;
                }
                else if (ch!='^' && index==3 && ch!='|')
                {taskdue=taskdue+ch;
                }
                else if (ch!='^' && index==4 && ch!='|')
                {if (ch=='M') taskpriority=true;
                }
                else if (ch=='^')
                {index++;
                }
                }
                    return tasks;
                }
        }
}
   
    

