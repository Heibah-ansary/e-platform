# Quick Start Guide - Employee Management System

## ⚡ 30-Second Setup

### Prerequisites
- Java Development Kit (JDK) 8 or higher installed
- Command line access or IDE

### Option 1: Command Line (Windows PowerShell)

```powershell
# Step 1: Navigate to project
cd EmployeeManagementSystem

# Step 2: Create output directory
mkdir bin

# Step 3: Compile
javac -d bin src/com/employeemgmt/model/*.java src/com/employeemgmt/service/*.java src/com/employeemgmt/app/*.java

# Step 4: Run
java -cp bin com.employeemgmt.app.EmployeeManagementSystem
```

### Option 2: Windows Command Prompt (CMD)

```cmd
cd EmployeeManagementSystem
mkdir bin
javac -d bin src\com\employeemgmt\model\*.java src\com\employeemgmt\service\*.java src\com\employeemgmt\app\*.java
java -cp bin com.employeemgmt.app.EmployeeManagementSystem
```

### Option 3: IntelliJ IDEA

1. File → Open → Select project folder
2. Right-click on `EmployeeManagementSystem.java`
3. Click "Run 'EmployeeManagementSystem.main()'"

### Option 4: Eclipse

1. File → Import → General → Existing Projects into Workspace
2. Browse to project folder → Finish
3. Right-click `EmployeeManagementSystem.java` → Run As → Java Application

### Option 5: VS Code

1. Install "Extension Pack for Java" (by Microsoft)
2. Open the project folder
3. Press `Ctrl + F5` to run, or
4. Click the "Run" icon above the main method

---

## 📁 Project Structure

```
EmployeeManagementSystem/
├── src/
│   └── com/employeemgmt/
│       ├── model/
│       │   └── Employee.java
│       ├── service/
│       │   └── EmployeeService.java
│       └── app/
│           └── EmployeeManagementSystem.java
├── bin/                        (Created after compilation)
├── README.md
└── SAMPLE_OUTPUT.md
```

---

## 🎯 First-Time Usage

1. **Add Employees** (Menu Option 1)
   - Enter employee details when prompted
   - System validates email and phone number

2. **View All** (Menu Option 2)
   - See all added employees in formatted table

3. **Search** (Menu Option 3)
   - Find by Employee ID or Name

4. **Update** (Menu Option 4)
   - Modify any employee details

5. **Sort & Filter** (Menu Option 6)
   - View advanced options
   - Sort by Salary, Name, or Department
   - Filter by Department or Salary Range

6. **Statistics** (Menu Option 7)
   - View salary analysis and department breakdown

---

## ✅ Troubleshooting

### "javac: command not found"
- **Issue:** Java compiler not in PATH
- **Solution:** Install JDK (not just JRE) and add JAVA_HOME to system PATH

### "Exception in thread 'main' java.lang.ClassNotFoundException"
- **Issue:** Compiled classes not found
- **Solution:** Ensure `-d bin` is used during compilation

### Invalid input errors
- **Issue:** Wrong data format
- **Solution:** Follow the exact format shown (email with @, phone with 10 digits)

### "Cannot connect to IDE"
- **Issue:** IDE not detecting Java
- **Solution:** Install Java Extension Pack (VS Code) or configure JDK path in IDE

---

## 📚 File Descriptions

| File | Purpose | Lines |
|------|---------|-------|
| Employee.java | Model class with data and getters/setters | ~160 |
| EmployeeService.java | Business logic and CRUD operations | ~350 |
| EmployeeManagementSystem.java | Main UI and menu handling | ~500 |
| **Total** | | **~1000** |

---

## 🔑 Key Features

✅ Add/View/Search/Update/Delete Employees  
✅ Sort by Salary, Name, Department  
✅ Filter by Department or Salary Range  
✅ View Statistics and Reports  
✅ Input Validation (Email, Phone)  
✅ Error Handling  
✅ Formatted Console Output  

---

## 💬 Example Session

```
Welcome to Employee Management System

1. Add New Employee
2. View All Employees
...
Enter choice: 1

Enter Name: John Doe
Enter Department: IT
Enter Designation: Developer
Enter Salary: 75000
Enter Email: john@example.com
Enter Phone: 9876543210

✅ Employee added successfully!

(System returns to main menu)

Enter choice: 2

Employees:
ID: 1001 | Name: John Doe | Salary: 75000.00 ...

Enter choice: 8

Thank you! Goodbye!
```

---

## 🚀 Next Steps

1. ✅ Understand the code by reading comments
2. ✅ Test all menu options
3. ✅ Experiment with invalid inputs to see error handling
4. ✅ Modify and extend features (e.g., add more fields)
5. ✅ Add database integration for persistence
6. ✅ Create unit tests with JUnit

---

## 📞 Support

Refer to `README.md` for:
- Detailed architecture explanation
- Interview questions
- Advanced features
- Design patterns used

---

**Happy Coding! 🎉**
