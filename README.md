# Spring: Spring Data 2
This is the repository for the LinkedIn Learning course Spring: Spring Data 2. The full course is available from [LinkedIn Learning][lil-course-url].

![Spring: Spring Data 2][lil-thumbnail-url] 

Coding to databases—which often involves the use of boilerplate code that's difficult to maintain and debug—can be tedious work. Upgrading applications to a different kind of data storage is similarly challenging, as it usually involves massive code rewrites. Spring Data addresses these issues by abstracting data store interactions into a common repository API and eliminating boilerplate code.

In this course, learn how to easily implement JPA-based repositories using Spring Data JPA. Instructor Mary Ellen Bowman introduces you to the Spring Data umbrella project and covers a handful of commonly used querying methods that you can start practicing on your own. From simple query methods with property expressions to more dynamic options like Querydsl, Query by Example (QBE), Spring Data REST, and MongoDB, Mary Ellen helps you build the skills you need to leverage the features of this powerful data management tool.



## Instructions
This repository has branches for each of the videos in the course. You can use the branch pop up menu in github to switch to a specific branch and take a look at the course at that stage, or you can add `/tree/BRANCH_NAME` to the URL to go to the branch you want to access.

## Branches
The branches are structured to correspond to the videos in the course. The naming convention is `CHAPTER#_MOVIE#`. As an example, the branch named `02_03` corresponds to the second chapter and the third video in that chapter. 
Some branches will have a beginning and an end state. These are marked with the letters `b` for "beginning" and `e` for "end". The `b` branch contains the code as it is at the beginning of the movie. The `e` branch contains the code as it is at the end of the movie. The `main` branch holds the final state of the code when in the course.

When switching from one exercise files branch to the next after making changes to the files, you may get a message like this:

    error: Your local changes to the following files would be overwritten by checkout:        [files]
    Please commit your changes or stash them before you switch branches.
    Aborting

To resolve this issue:
	
    Add changes to git using this command: git add .
	Commit changes using this command: git commit -m "some message"


### Instructor

Mary Ellen Bowman 
                            
Senior Software Developer

                            

Check out my other courses on [LinkedIn Learning](https://www.linkedin.com/learning/instructors/mary-ellen-bowman).

[lil-course-url]: https://www.linkedin.com/learning/spring-spring-data-2-18748916?dApp=59033956
[lil-thumbnail-url]: https://media.licdn.com/dms/image/C4D0DAQFKjea5yMgDKA/learning-public-crop_675_1200/0/1674159455671?e=2147483647&v=beta&t=VgxGPbs4KkErBHl_N5VYRXRX82WjP91N1GG60oqcgHA
