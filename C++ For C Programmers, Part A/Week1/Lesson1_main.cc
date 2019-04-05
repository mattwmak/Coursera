/*
 * Lesson1_main.cc
 *  Created on: Mar 3, 2019
 *      Author: User
 */

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
