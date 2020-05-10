# Technical debt

## Technical debt indicators analysis

* unclear/unreadable code - doesn't exist
* redundant code - exists     
* automation absence (tests, build, deployment) - code is covered by tests, but not whole
* confusing architecture - exists
* slow/weak parts - exist
* not committed code/long-lived branches - doesn't exist
* technical documentation - partically exists
* testing environment - exists
* long integration time/existence of continuous integration - CI doesn't exist

## Events to eliminate technical debt

SP is measured like in development process.
1 SP is 2 person-hour.

| Problem | Event | SP |
|:---|:---|:---|
| Redundant code | Extract common code into external function | 3 |
| Whole code isn't covered by tests | Covering most of the code with tests | 4 |
| Slow/weak parts existence | Analyse the system (by profiling) and find this points. Try to fix problems or find a way to remake these. | 4 |
| Confusing architecture existence | Try to distribute system to smaller parts. | 4 |
| Full documentation is missing | Create a full documentation list and make these points | 2 |

## Conclusion

 Partially technical debt elimination during current sprint and another one sprint creation to completely eliminate the technical debt will ensure to solve many problems. Code will be covered by tests and it will help developers to find bugs quickly. Also architecture will become clearer and be open for new features integration. Addition documentation will ensure users to familiar with features and functionality of application.
