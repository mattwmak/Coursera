//============================================================================
// Name        : Assignment.cpp
// Author      : 
// Version     :
// Copyright   : Your copyright notice
// Description : Hello World in C++, Ansi-style
//============================================================================

/* Objective:
* Implement a Monte Carlo simulation that calculates the average shortest path in a graph.
* The graph can be generated using a pseudo-random number generator to produce edges and their costs.
* The shortest path algorithm will be Dijkstra’s. Specifics are in the "My Submission" tab.
*/

#include <iostream>
#include <limits>
#include <climits>
#include <memory>
#include <algorithm>

using namespace std;

const int default_nodes = 50;
const int default_density = 40;
const int default_distance = 10;
const int invalid = -1;
const int inf = INT_MAX;

class Dijkstra{
private:
	//class variables
	int nodes;							//total number of nodes within graph
	float density;						//selects the amount of nodes to be selected for edge creation
	bool * activity;
	int * track;
	int **weight;
public:
	Dijkstra(int N, float D);				//constructor
	~Dijkstra();
	bool readValues();
	bool isValidNode(int);
	void shortestPath(int, int);
};

Dijkstra :: Dijkstra(int N, float D){
	nodes = N;
	density = D;
	track = new int[N];
	activity = new bool[N];
	weight = new int * [N];

	float threshold = (density*N);	//the numbers for this won't overflow a type int
	for(int i=0;i<N;i++){
		weight[i]=new int[N];								//init weight array
		//randomly determine which nodes are active.
		float rando = rand()%N;
		activity[i]=(rando <= threshold)?true:false;		//sets the node to active/non-active
		track[i] = (activity[i])?0:invalid;					//sets undefined tracked values so it doesn't interfere with application
	}

	//set active nodes via density values while configuring weighted matrix
	for(int i=0;i<N;i++){
		//set weight of edges here.
		for(int j=0;j<N;j++){
			if(i<=j){
				if(activity[i]&&activity[j]){
					weight[i][j] = (i!=j)?rand()%10:0;//(rand()%10):0;//weight[i][j]=rand()%10;		//set weight 0-9..+1 -> 1-10
					weight[j][i]=weight[i][j];	//mirrored path.
				}
				else{
					weight[i][j]=weight[j][i]=0;
				}
			}
			//if(i==j) weight[i][j]=0;
		}
	}
}

Dijkstra :: ~ Dijkstra(){
	for (int i = 0; i < nodes; i++) {
	    delete[] weight[i];
	}
	delete[] weight;
    delete[] track;
    delete[] activity;
}

bool Dijkstra :: readValues(void){
	bool valid= false;
	std::cout<< "generated weight array" << endl;
	for(int i=0; i<nodes; i++){
		for(int j=0; j<nodes; j++){
			std::cout << " " << weight[i][j] ;
		}
		std:: cout << " " << endl;
	}
	std:: cout << " " << endl;

	std::cout<< "valid nodes: ";
	for(int i=0; i< nodes; i++){
		if(activity[i]){
			valid = true;
			std::cout << " "<< i+1;
		}
	}
	std::cout<< " "<< endl;
	return valid;
}

bool Dijkstra :: isValidNode(int node){
	cout << node << " " << activity[node-1] << endl;
	return activity[node-1];
}
void Dijkstra :: shortestPath(int start, int end){
	//1. check possible routes to take. add lowest weight to the tentative weight of node.
	//2. if the tentative weight of the node is less than
	int tent_weight[nodes];
	int curr = start;

	bool unvisited[nodes];
	bool visited[nodes];

	for(int i=0; i<nodes;i++){
		tent_weight[i]=(track[i]==invalid)?invalid:inf;
		unvisited[i] = (track[i]==invalid)?false:true;
		visited[i] = false;
	}
	tent_weight[curr]=0;

	while(visited[end]==false){
		for(int i=0; i< nodes;i++){
			if(unvisited[i] == true){
				tent_weight[i] = std::min(tent_weight[curr]+weight[curr][i],tent_weight[i]);
			}
		}
		visited[curr]=true;
		unvisited[curr]=false;

		bool get_firstlowest = true;
		for(int i=0; i< nodes;i++){
			if(unvisited[i]==true){
				if(get_firstlowest)
					curr = i;
				else
					curr = (tent_weight[i] < tent_weight[curr])?i:curr;
			}
		}
	}
	cout << "shortest path: " << tent_weight[end] << endl;
}

int main() {
	float density;
	int nodes;

	cout << "Enter number of nodes (1-50):" <<endl;
	cin >> nodes;
	while(nodes<=0 ||nodes>50){
		if(cin.fail()){
			cout << "characters not supported"<<endl;
			cin.clear(); //clear bad input flag
		    cin.ignore(std::numeric_limits<std::streamsize>::max(), '\n'); //discard input
		}
		cout << "Error. Enter number of nodes (1-50):" <<endl;
		cin >> nodes;
	}

	cout << "Enter density value (0.2 or 0.4): " << endl;
	cin >> density;
	while(density!=(float)0.2 && density != (float)0.4){
		if(cin.fail()){
			cout << "characters not supported"<<endl;
			cin.clear(); //clear bad input flag
			cin.ignore(std::numeric_limits<std::streamsize>::max(), '\n'); //discard input
		}
		cout << "Error. Enter density value (0.2 or 0.4):" <<endl;
		cin >> density;
	}
	Dijkstra obj(nodes, density);
	if(!obj.readValues()){
		cout << "no valid nodes" << endl;
		obj.~Dijkstra();
		return 0;
	}

	int startNode;
	int endNode;
	cout << "Enter Start Node: " << endl;
	cin >> startNode;
	while(!obj.isValidNode(startNode)){
		if(cin.fail()){
			cin.clear(); //clear bad input flag
			cin.ignore(std::numeric_limits<std::streamsize>::max(), '\n'); //discard input
		}
		cout << "Not a valid Node. Enter a Start Node." << endl;
		cin >> startNode;
	}
	while(1){
		cout << "Enter a End Node. Or enter '0' to end program" << endl;
		cin >> endNode;
		if(endNode==0) break;
		else if(cin.fail()){
			cin.clear(); //clear bad input flag
			cin.ignore(std::numeric_limits<std::streamsize>::max(), '\n'); //discard input
		}
		else{
			obj.shortestPath(startNode-1, endNode-1);
		}
	}
	obj.~Dijkstra();
	return 0;
}
