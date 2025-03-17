import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class TaskGui extends JFrame {
    private Taskmanager taskManager;    
    private JList<String> taskList;
    private DefaultListModel<String> taskListModel;
    private JTextField taskNameField, taskDescField, taskDateField;
    private JComboBox<String> priorityComboBox;

    public TaskGui() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        taskManager = new Taskmanager();
        setTitle("Task Manager");
        setSize(screenSize.width, screenSize.height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Font mare pentru text
        Font labelFont = new Font("Arial", Font.BOLD, 24);
        Font fieldFont = new Font("Arial", Font.PLAIN, 24);
        Font buttonFont = new Font("Arial", Font.BOLD, 24);

        // Panel pentru introducerea datelor
        JPanel taskPanel = new JPanel();
        taskPanel.setLayout(new GridLayout(4, 2, 10, 10));

        JLabel nameLabel = new JLabel("Task Name:");
        nameLabel.setFont(labelFont);
        taskPanel.add(nameLabel);

        taskNameField = new JTextField();
        taskNameField.setFont(fieldFont);
        taskPanel.add(taskNameField);

        JLabel descLabel = new JLabel("Description:");
        descLabel.setFont(labelFont);
        taskPanel.add(descLabel);

        taskDescField = new JTextField();
        taskDescField.setFont(fieldFont);
        taskPanel.add(taskDescField);

        JLabel dateLabel = new JLabel("Due Date (use '/', ex:01/05/2005):");
        dateLabel.setFont(labelFont);
        taskPanel.add(dateLabel);

        taskDateField = new JTextField();
        taskDateField.setFont(fieldFont);
        taskPanel.add(taskDateField);

        JLabel priorityLabel = new JLabel("Priority:");
        priorityLabel.setFont(labelFont);
        taskPanel.add(priorityLabel);

        String[] priorities = {"Mandatory", "Optional"};
        priorityComboBox = new JComboBox<>(priorities);
        priorityComboBox.setFont(fieldFont);
        taskPanel.add(priorityComboBox);

        add(taskPanel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));

        JButton addButton = new JButton("Add Task");
        JButton removeButton = new JButton("Remove Task");
        JButton saveButton = new JButton("Save Task");
        JButton loadButton = new JButton("Load Task");
        JButton clearButton=new JButton("Clear All");

        addButton.setPreferredSize(new Dimension(250, 60));
        removeButton.setPreferredSize(new Dimension(250, 60));
        saveButton.setPreferredSize(new Dimension(250, 60));
        loadButton.setPreferredSize(new Dimension(250, 60));
        clearButton.setPreferredSize(new Dimension(250,60));

        addButton.setFont(buttonFont);
        removeButton.setFont(buttonFont);
        saveButton.setFont(buttonFont);
        loadButton.setFont(buttonFont);
        clearButton.setFont(buttonFont);

        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(saveButton);
        buttonPanel.add(loadButton);
        buttonPanel.add(clearButton);

        add(buttonPanel, BorderLayout.CENTER);
        
        taskListModel = new DefaultListModel<>();
        taskList = new JList<>(taskListModel);
        taskList.setFont(fieldFont);
        JScrollPane scrollPane = new JScrollPane(taskList);
        scrollPane.setPreferredSize(new Dimension(screenSize.width,(int)(screenSize.height / 1.4f)));
        add(scrollPane, BorderLayout.SOUTH);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addTask();
            }
        });

        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeTask();
            }
        });

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Taskfile.saveTask(taskManager.getTasks());
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    taskManager.setTasks(Taskfile.readTask());
                    updateTaskList();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
       clearButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
            taskManager.clearTasks();
            updateTaskList();
            }   
        });
    }

    private void addTask() {
        String name = taskNameField.getText();
        String description = taskDescField.getText();
        String dueDate = taskDateField.getText();
        String priority = (String) priorityComboBox.getSelectedItem();
        Task task = new Task(name, description, dueDate, priority.equals("Mandatory"));
        taskManager.addTask(task);
        taskManager.sortTaskPriority();
        taskManager.sortTaskDue();
        updateTaskList();

        taskNameField.setText("");
        taskDescField.setText("");
        taskDateField.setText("");
    }

    private void removeTask() {
        int selectedIndex = taskList.getSelectedIndex();
        if (selectedIndex != -1) {
            taskManager.removeTask(selectedIndex);
            taskManager.sortTaskPriority();
            taskManager.sortTaskDue();
            updateTaskList();
        }
    }

    private void updateTaskList() {
        taskListModel.clear();
        int index=1;
        ArrayList<Task> tasks = taskManager.getTasks();
        for (Task task : tasks) {
            long daysLeft=task.daysUntilDue();  
            String howlonguntil;
            if (daysLeft>0)
            {
                howlonguntil="Due in "+daysLeft+" days";
            }
            else if (daysLeft==0)
            {
                howlonguntil="Due today";
            }
            else
            {
                howlonguntil="Overdue by "+Math.abs(daysLeft)+" days";
            }
            taskListModel.addElement(index+"     "+task.getTaskName() + "   " + task.getTaskDescription() + "   " + task.getTaskDue()+"   "+howlonguntil);
            index++;
        }
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                TaskGui gui = new TaskGui();
                gui.setVisible(true);
            }
        });
    }
}
