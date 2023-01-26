#include <iostream>
#include <string>

using namespace std;

//----Draw Functions----
void drawRow(int row){
    string cell = "=======|    x||     |=======";
    int cellSize = cell.length()/4;

    for (int i = 0; i < cell.length(); i += cellSize){
        for (int j = row*7; j < (row*7)+7; j++){
            string cellRow = cell.substr(i, cellSize);
            int dayOfMonth = (j <= 31) ? j+1 : j+1 - 31;


            if (cellRow.find("x") == string::npos){
                cout << cellRow;
                continue;
            }

            cout << cellRow.substr(0, cellRow.find("x")) << dayOfMonth << cellRow.substr(cellRow.length(), 1);
        }
        cout << endl;
    }
}
void drawMonth(int month){
    cout << "\t\t\t" << month << endl;
    for (int i = 0; i < 5; i++){
        drawRow(i);
    }
}
//----Math Functions----
int monthFromDate(const string &date){
    int splitIndex = date.find("/");
    return stoi(date.substr(0, splitIndex));
}
int dayFromDate(const string &date){
    int splitIndex = date.find("/");
    return stoi(date.substr(splitIndex+1, date.length()-splitIndex));
}

//----Display Function----
void displayDate(int month, int day){
    cout << "Month: " << month << endl;
    cout << "Day: " << day << endl;
}

//----Output Managing Functions----
void pauseOutput(const string &message){
    cout << message;
    cin;
    cin.ignore();
}
void clearOutput(){
    for (int i = 0; i < 20; i++){
        cout << endl;
    }
}


int main() {
    string calendarPicture = "┌───────────────────────────────────────────────┐\n│                                    ===========│\n│                                    ===========│\n│                                      =========│\n│                 #######              =========│\n│                ##     ###            =========│\n│               ##@ v @   ##            ========│\n│       =       #     ###  #            ========│\n│       ==      #      ## ###           ========│\n│        ====   ###      #####     =============│\n│           ==    ########    ==================│\n│          ========^===^========================│\n│                       ========================│\n│    bird             ===              =========│\n│                                        =======│\n│                                       ========│\n│                                       ========│\n│                                       ========│\n│                                       ========│\n└───────────────────────────────────────────────┘";

    clearOutput();
    cout << "Welcome to Calendar" << endl;

    string userDate;
    cout << "What date would you like to look at? (mm/dd)" << endl << ">";
    getline(cin, userDate);

    int inputMonth = monthFromDate(userDate);
    int inputDay = dayFromDate(userDate);

    clearOutput();
    cout << calendarPicture << endl;
    drawMonth(inputMonth);
    displayDate(inputMonth, inputDay);
    pauseOutput("\n\nPress enter to continue...");

    clearOutput();
    cout << "This Month: " << endl;
    cout << calendarPicture << endl;
    //drawMonth(currentMonth);
    //displayDate(currentMonth, currentDay);
    pauseOutput("\n\nPress enter to quit...");

    return 0;
}


