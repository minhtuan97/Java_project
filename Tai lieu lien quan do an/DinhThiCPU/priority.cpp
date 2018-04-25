#include <stdio.h>
#include <string.h>


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
    int priority = 100000;
};

int sumOfBurst(process* p, int n){
    int temp_sum = 0;

    for (int i = 0; i < n; i++){
        temp_sum += p[i].rootBurst;
    }

    return temp_sum;
}

int execProcess(process* p, int n, int time){
    int process = -1;
    int _min  = 1000;

    for (int i = 0; i < n; i++){
        if((p[i].arrival <= time) && (p[i].burst > 0) && (p[i].priority < _min)){
            process = i;
            _min = p[i].priority;
        }
    }

    return process;
}
int main()
{
//Input
    int n;
    process* p = new process[n];
    printf("input \"n\" process: ");   scanf("%d", &n) ;




   for(int i = 0; i < n; i++){
        printf( "NP, Arrival, Burst, priority: ");
        scanf ("%d %d %d %d",&p[i].nameProcess, &p[i].arrival, &p[i].burst, &p[i].priority);
   }



//Pre Exec
    // Initalise rootBurst to backup :D
    for (int i = 0; i < n; i++){
        p[i].rootBurst = p[i].burst;
    }

// Exec
    int rootSum = sumOfBurst(p, n);
    int process;
    int time = 0;
    for(int i = 0 ; i < n; i++){
        process = execProcess(p, n, time);
        p[process].start = time;
        p[process].finished = time + p[process].burst;


        if(process == -1)
            break;

        time += p[process].burst;
        p[process].burst = 0;
        p[process].priority = 10000;
    }


    for(int i = 0; i < n; i++){
        p[i].tat = p[i].finished - p[i].arrival;
        p[i].respond = p[i].start - p[i].arrival;
        p[i].waiting = p[i].tat - p[i].rootBurst;
    }

//Output
    printf("process name \t Arrival \t TAT \t Respond \t Waiting \n");
    for(int i = 0;  i < n; i++){
        printf("%d \t \t %d \t \t %d \t \t %d \n",
               p[i].nameProcess, p[i].arrival, p[i].respond, p[i].waiting);
    }

    return 0;
}
