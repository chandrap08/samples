#include <cmath>
#include <cstdio>
#include <vector>
#include <iostream>
#include <algorithm>
using namespace std;

int n, m, l;
char mas[110][110];
int cnt = 0;
int dx[] = {1,-1,0,0};
int dy[] = {0,0,-1,1};

bool norm(int x, int y)
{
  return x >= 0 && y >= 0 && x < n && y < m;
}

bool dfs(int x, int y, int px=-1, int py=-1)
{
    //cout << x << " " << y << "\n";
    //return 0;
    if(mas[x][y] == '*') return 1;
    int cc = 0;
    bool fl = 0;
    for(int i = 0 ; i < 4 ; ++i)
    {
      int nx = x + dx[i];
        int ny = y + dy[i];
        if(!norm(nx, ny)) continue;

        if(nx == px && ny == py) continue;
        if(mas[nx][ny] == 'X') continue;

        if(dfs(nx, ny, x, y))
            fl = 1;

        ++cc;
    }
    if(fl && cc > 1)
        ++cnt;
    return fl;
}

int main() {
    int tt;
    cin >> tt;
    while(tt --> 0)
    {
        cin >> n >> m;
        int sx, sy;
        for(int i = 0 ; i < n ; ++i)
            for(int j = 0 ; j < m ; ++j)
          {
              cin >> mas[i][j];
              //cout << mas[i][j] << "\n";
            if(mas[i][j] == 'M')
                    sx = i, sy = j;
          }
        cin >> l;
        cnt = 0;
        dfs(sx, sy);
        if(cnt != l)
            cout << "Oops!\n";
        else
            cout << "Impressed\n";
    }
    return 0;
}

