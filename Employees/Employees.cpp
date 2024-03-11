#include <iostream>
#include <string>
using namespace std;

void mainActions() {
    cout << "\n------------------" << endl;
    cout << "Select Section:" << endl;
    cout << "(1) Jobs: " << endl;
    cout << "(2) Departments: " << endl;
    cout << "(3) Branch: " << endl;
    cout << "(4) Employees: " << endl;
}

void jobActions() {
    cout << "------------------" << endl;
    cout << "Select Action:" << endl;
    cout << "(1) Add Job: " << endl;
    cout << "(2) Edit Job: " << endl;
    cout << "(3) Show Jobs: " << endl;
}

void branchActions() {
    cout << "------------------" << endl;
    cout << "Select Action:" << endl;
    cout << "(1) Add Branch: " << endl;
    cout << "(2) Edit branch: " << endl;
    cout << "(3) Show Branches: " << endl;
}

void deptActions() {
    cout << "------------------" << endl;
    cout << "Select Action:" << endl;
    cout << "(1) Add Department: " << endl;
    cout << "(2) Edit Department: " << endl;
    cout << "(3) Show Departments " << endl;
}

void empActions() {
    cout << "------------------" << endl;
    cout << "Select Action:" << endl;
    cout << "(1) Add Employee: " << endl;
    cout << "(2) Edit Employee: " << endl;
    cout << "(3) Show Employee: " << endl;
}

// add element to array
void addEleme(string txt, string arr[], int len) {
    int s = 0;
    for (int i = 0; i < len; i++) {
        if (arr[i].length() == 0) {
            s = i;
            break;
        }
    }
    arr[s] = txt;
}

// get max length
int maxLength(string arr[], int len) {
    int a = arr[0].length();
    for (int i = 0; i < len; i++) {
        if (arr[i].length() > a) {
            a = arr[i].length();
        }
    }
    return a;
}

// write string in specific length 
string ljust(string txt, int l) {
    int len = txt.length();
    while (len < l) {
        txt += " ";
        len++;
    }
    return txt;
}

// print array as a tbale
void printArr(string arr[], int len) {
    int ml = maxLength(arr, len) + 5;
    for (int i = 0; i < len; i++) {
        if (arr[i].length() == 0) { continue; }
        string x = to_string(i + 1);
        cout << ljust(x, 10) << ljust(arr[i], ml) << endl;
    }
}

void printArr1(string arr1[],string arr2[],string arr3[],string arr4[],string arr5[],string arr6[],string arr7[], string arr8[],int len) {
    for (int i = 0; i < len; i++) {
        if (arr1[i].length() == 0) { continue; };
        cout << ljust((arr1[i]), 30) << ljust(arr3[i], 30) << ljust(arr4[i], 30)<< ljust(arr5[i], 30)<< ljust(arr6[i], 30)<< ljust(arr7[i], 30)<< ljust(arr8[i], 30) << endl;
    }
}

// to print array in same line
void printArrH(string arr[], int len) {
    int b = len;
    for (int i = 0; i < len; i++) {
        if (arr[i].length() == 0) {
            b = i - 1;
            break;
        }
    }
    for (int i = 0; i <= b; i++) {
        if (i < b) {
            cout << "(" << i + 1 << ") " << arr[i] << ", ";
        }
        else {

            cout << "(" << i + 1 << ") " << arr[i];
        }
    }
}


// gender
void gen() {
    cout << "Gender:" << endl;
    cout << "(1) Male,  (2) Female" << endl ;
}

int main()
{
    
    int secSel;
    int jobSel;
    int deptSel;
    int branchSel;
    int empSel;
    string jobTitle[50] = {};
    string deptName[50] = {};
    string branchName[10] = {};
    string first[100] = {};
    string last[100] = {};
    string jobs[100] = {};
    string departments[100] = {};
    string branches[100] = {};
    string salaries[100] = {};
    string gender[100] = {};
    string age[100] = {};


start:
    string njob;
    string ndept;
    string nbranch;
    string nemp;
    int ejob;
    string e_job;
    int edept;
    string e_dept;
    int ebranch;
    string e_branch;
    string nFirst;
    string nLast;
    int i_job;
    string n_jobs;
    int i_dept;
    string n_depts;
    int i_branch;
    string n_branches;
    int g;
    string gText;
    string n_salary;
    string n_age;

    mainActions();
    cout << "Select Section: ";
    cin >> secSel;

    switch (secSel) {
    case 1:
        jobActions();

        cout << "Select Action: ";
        cin >> jobSel;

        switch (jobSel) {

        case 1:
            cout << "Enter Newe Job: ";
            cin >> njob;
            addEleme(njob, jobTitle, 50);
            break;

        case 2:
         
            cout << ljust("ID", 10) << ljust("Job Title", 20) << endl;
            printArr(jobTitle, 50);
            cout << "Select id to edit: ";
            cin >> ejob;
            cout << "Edit Job Title: ";
            cin >> e_job;
            jobTitle[ejob - 1] = e_job;
            break;

        case 3:
            cout << ljust("ID", 10) << ljust("Job Title", 20) << endl;
            printArr(jobTitle, 50);
            break;

        default :
            cout << "Invalid Selection Number." << endl;
            
        }

        break;

    case 2:
        deptActions();
        cout << "Select Action: ";
        cin >> deptSel;

        switch (deptSel) {

        case 1:
            cout << "Enter Newe Department: ";
            cin >> ndept;
            addEleme(ndept, deptName, 50);
            break;

        case 2:

            cout << ljust("ID", 10) << ljust("Department", 20) << endl;
            printArr(deptName, 50);
            cout << "Select id to edit: ";
            cin >> edept;
            cout << "Edit Department: ";
            cin >> e_dept;
            deptName[edept - 1] = e_dept;
            break;

        case 3:
            cout << ljust("ID", 10) << ljust("Department", 20) << endl;
            printArr(deptName, 50);
            break;

        default:
            cout << "Invalid Selection Number." << endl;

        }
        break;

    case 3:
        branchActions();
        cout << "Select Action: ";
        cin >> branchSel;

        switch (branchSel) {

        case 1:
            cout << "Enter Newe Branch: ";
            cin >> nbranch;
            addEleme(nbranch, branchName, 50);
            break;

        case 2:

            cout << ljust("ID", 10) << ljust("Branch Name", 20) << endl;
            printArr(branchName, 10);
            cout << "Select id to edit: ";
            cin >> ebranch;
            cout << "Edit Branch Name: ";
            cin >> e_branch;
            branchName[ebranch - 1] = e_branch;
            break;

        case 3:
            cout << ljust("ID", 10) << ljust("Branch Name", 20) << endl;
            printArr(branchName, 10);
            break;

        default:
            cout << "Invalid Selection Number." << endl;

        }
        break;

    case 4:
        empActions();
        cout << "Select Action: ";
        cin >> empSel;

        switch (empSel) {

        case 1:
            cout << "Enter Employee Name: ";
            cin >> nFirst >> nLast;

            addEleme(nFirst, first, 100);
            addEleme(nLast, last, 100);

            cout << "jobs:" << endl;
            printArrH(jobTitle, 50);
            cout << "\nSelect Job: ";
            cin >> i_job;
            n_jobs = jobTitle[i_job - 1];
            addEleme(n_jobs, jobs, 100);

            cout << "Departments:" << endl;
            printArrH(deptName, 50);
            cout << "\nSelect Department: ";
            cin >> i_dept;
            n_depts = deptName[i_dept - 1];
            addEleme(n_depts, departments, 100);


            cout << "Branches:" << endl;
            printArrH(branchName, 50);
            cout << "\nSelect Branch: ";
            cin >> i_branch;
            n_branches = branchName[i_branch - 1];
            addEleme(n_branches, branches, 100);

            gen();
            cout << "Select Gender";
            cin >> g;

            gText = (g == 1) ? "Male" : "Female";

            addEleme(gText, gender, 100);

            cout << "Enter Salary: ";
            cin >> n_salary;

            addEleme(n_salary, salaries, 100);


            cout << "Enter Age: ";
            cin >> n_age;

            addEleme(n_age, age, 100);

            break;

        case 2:
            break;

        case 3:
            printArr1(first, last, jobs, departments, branches, gender, salaries, age, 100);
            break;

        default:
            cout << "Invalid Selection Number." << endl;
            break;
        }

        break;

    default:
        cout << "Invalid Selection Number." << endl;
        break;
    }

    goto start;

    return 0;
}

