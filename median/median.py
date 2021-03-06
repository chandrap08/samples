from bisect import *
from collections import defaultdict

def main():
    ndict = defaultdict(int)
    nums = []
    l = 0
    n = int(raw_input())
    for i in range(n):
        #print nums
        command, num = [i for i in raw_input().split()]
        num = int(num)
        if command == 'r':
            if num in ndict:
                l -= 1
                ndict[num] -= 1
                nums.remove(num)
                if ndict[num] == 0:
                    del ndict[num]
            else:
                print 'Wrong!'
                continue
            if l % 2 and l > 0:
                print nums[l/2]
            elif l > 0:
                outnum = (nums[l/2] + nums[(l/2)-1]) / 2.0
                outnum = int(outnum) if outnum.is_integer() else outnum
                print outnum 
            else:
                print 'Wrong!'
        else:
            l += 1
            insort(nums, num)
            ndict[num] += 1
            if l % 2:
                print nums[l/2]
            else:
                outnum = (nums[l/2] + nums[(l/2)-1]) / 2.0
                outnum = int(outnum) if outnum.is_integer() else outnum
                print outnum


if __name__ == "__main__":
    main()
                    

