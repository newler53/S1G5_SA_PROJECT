"# S1G5_SA_PROJECT" 

cls
mkdir S1G5_SA_PROJECT
type nul > test.txt
start .
code .
echo "# S1G5_SA_PROJECT" >> README.md
git init
git add README.md
git commit -m "first commit"
git remote add origin https://github.com/newler53/S1G5_SA_PROJECT.git
git push -u origin master
q
git log
git config --global --list
git config --global user.name "newler53"
git config --global user.email "newler53@gmail.com"
git push
git commit -a -m "first1 commit"