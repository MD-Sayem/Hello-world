#include<cstdio>
#include<bits/stdc++.h>
#include<stdio.h>
#include<stdlib.h>
#include<cmath>
using namespace std;
int s;
void blank(int j)
{
    if(j)
        cout << " ";
    for(int i=1; i<=(s+2); i++)
        cout << " ";
}
void slash_back(int j)
{
    if(j)
        cout << " ";
    for(int i=1; i<=(s+2); i++)
        if (i==(s+2))
            cout << "|";
        else
            cout << " ";
}
void slash_dual(int j)
{
    if(j)
     cout << " ";
    for(int i=1; i<=(s+2); i++)
        if (i==1||i==(s+2))
            cout << "|";
        else
            cout << " ";
}
void slash_front(int j)
{
    if(j)
     cout << " ";
    for(int i=1; i<=(s+2); i++)
        if (i==1)
            cout << "|";
        else
            cout << " ";
}
void dot_dot(int j)
{
    if(j)
        cout << " ";
     for(int i=1; i<=(s+2); i++)
        if (i==1||i==(s+2))
            cout << " ";
        else
            cout << "-";

}
int main()
{
    int j,l;
    char num[1024];
    while(1)
    {
        scanf("%d%s",&s,num);
        if(s==0&&!strcmp(num,"0"))
            break;
        l=strlen(num);
        for(int i=1; i<=(2*s+3); i++)
        {
            if(i==1)        // printing top line
            {
                for(int j=0; j<l; j++)
                if(num[j]=='1'||num[j]=='4')
                    blank(j);
                else
                    dot_dot(j);
            }
            else if(i>1&&i<(s+2))       // printing lines between top and mid
            {
                 for(int j=0; j<l; j++)
                if(num[j]=='1'||num[j]=='2'||num[j]=='3'||num[j]=='7')
                    slash_back(j);
                else if  (num[j]=='5'||num[j]=='6')
                    slash_front(j);
                else
                    slash_dual(j);
            }
            else if(i==(s+2))  // printing mid line
             {
                for(int j=0; j<l; j++)
                if(num[j]=='1'||num[j]=='7'||num[j]=='0')
                    blank(j);
                else
                    dot_dot(j);
            }
            else if(i>(s+2)&&(i<(2*s+3)))   // printing lines between mid and bottom
            {
                    for(int j=0; j<l; j++)
                if(num[j]=='6'||num[j]=='8'||num[j]=='0')
                    slash_dual(j);
                else if  (num[j]=='2')
                    slash_front(j);
                else
                    slash_back(j);
            }
            else        //printing the bottom line
                  for(int j=0; j<l; j++)
                if(num[j]=='1'||num[j]=='4'||num[j]=='7')
                    blank(j);
                else
                    dot_dot(j);
            cout << endl;

            }
        cout << endl;
    }
    return 0;
}
/*
1 1234567890
2 1234567890
3 01317247941
4 1234567890
5 1234567890
6 1234567890
7 1234567890
8 1234567890
9 1234567890
10 1234567890
0 0
*/
