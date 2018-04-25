#include <iostream>
#include <string.h>
using namespace std;

struct process{
    int nameProcess;
    int burst;
    int arrival = 0;
    int finished = 0;
    int start = 0;
    int tat = 0;
    int respond = 0;
    int waiting = 0;
    int rootBurst = 0;
};

int sumOfBurst(process* p, int n){
    int temp_sum = 0;

    for (int i = 0; i < n; i++){
        temp_sum += p[i].burst;
    }

    return temp_sum;
}

int execProcess(process* p, int n, int time){
    int process = -1;
    int _min  = 1000;

    for (int i = 0; i < n; i++){
        if((p[i].arrival <= time) && (p[i].burst > 0) && (p[i].burst < _min)){
            process = i;
            _min = p[i].burst;
        }
    }

    return process;
}
int main()
{
//Input
    int n = 4;
    process* p = new process[n];
    cout << "input \"n\" process: ";   cin >> n;




   for(int i = 0; i < n; i++){
        cout << "NP, Arrival, Burst ";
        cin >> p[i].nameProcess >> p[i].arrival >> p[i].burst;
    }

//Pre Exec
    // Initalise rootBurst to backup :D
    for (int i = 0; i < n; i++){
        p[i].rootBurst = p[i].burst;
    }

// Exec
    int rootSum = sumOfBurst(p, n);
    int process;
    for(int i = 0 ; i <= rootSum; i++){
        if( i > 0 ){
            p[process].burst -= 1;
            if(p[process].burst == 0){
                p[process].finished = i;
            }
        }


        process = execProcess(p, n, i);
        if (process == -1)
            break;


        if (p[process].burst == p[process].rootBurst){
            p[process].start = i;
        }
    }

    for(int i = 0; i < n; i++){
        p[i].tat = p[i].finished - p[i].arrival;
        p[i].respond = p[i].start - p[i].arrival;
        p[i].waiting = p[i].tat - p[i].rootBurst;
    }

//Output
    cout << "process name \t Arrival \t TAT \t Respond \t Waiting \t  Finished \t Start \t rootBurst \n";
    for(int i = 0;  i < n; i++){
        cout << p[i].nameProcess
             << "\t \t" << p[i].arrival
             << "\t \t" << p[i].tat
             << "\t \t" << p[i].respond
             << "\t \t" << p[i].waiting
             << "\t \t" << p[i].finished
             << "\t \t" << p[i].start
             << "\t \t" << p[i].rootBurst
             << "\n";
    }

    return 0;
}
