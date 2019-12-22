class ComparisonClass{
public:
bool operator() (pair<int,int> a, pair<int,int> b) {
        return a.s<b.s;
    }
};
/*
priority_queue<pii,vector<pii>,ComparisonClass> q;
*/