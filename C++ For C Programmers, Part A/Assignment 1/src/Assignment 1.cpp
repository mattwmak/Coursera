//============================================================================
// Name        : Assignment.cpp
// Author      : Matthew Mak
// Version     :
// Copyright   : Your copyright notice
// Description : Assignment 1 -- coursera
//============================================================================

#include <iostream>
#include <vector>

using namespace std;
const int N = 40;

inline void sum(int* val, int total, vector<int> v1){
	for(int i=0; i< total; i++){
		*val = *val + v1.at(i);
	}
}

int main() {
	int accum = 0;
	vector<int> v1;

	for(int i=0;i<N;i++)
		v1.push_back(i);

	sum(&accum, N, v1);
	cout << "Total Sum: " << accum << endl;
	return 0;
}

/****Alternative solution***/
/*
#include <iostream>
#include <vector>

using namespace std;
const int N = 40;

inline void sum(int *p, vector<int> d)
{
	for(vector<int>::iterator arr = d.begin(); arr != d.end();++arr)
		*p = *p + *arr;
}

int main()
{
  	int accum = 0;
	std::vector<int> data;

	for(int i=0; i< N; ++i)
		data.push_back(i);

	sum(&accum,data);
	cout << "sum is " << accum << endl;
	return 0;
}
*/
