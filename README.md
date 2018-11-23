# Traffic Control [![GitHub license](http://img.shields.io/badge/license-MIT-blue.svg?style=flat-square)](https://github.com/meik99/TrafficControl/blob/master/LICENSE)

| Branch        | Status        | Coverage | Information |
| ------------- | --------------| ------- | ------- |
| **master**        | [![Build Status](https://img.shields.io/travis/meik99/TrafficControl/master.svg?style=flat-square)](https://travis-ci.org/lukasbindreiter/white-brush) | [![Coverage Status](https://img.shields.io/coveralls/lukasbindreiter/white-brush/master.svg?style=flat-square)](https://coveralls.io/github/lukasbindreiter/white-brush?branch=master) | [Download](https://github.com/lukasbindreiter/white-brush/issues?q=is%3Aopen+assignee%3AShynixn)
| Christoph   | [![Build Status](https://img.shields.io/travis/meik99/TrafficControl/developer/christoph.svg?style=flat-square)](https://travis-ci.org/lukasbindreiter/white-brush) | [![Coverage Status](https://img.shields.io/coveralls/lukasbindreiter/white-brush/developer/lukas.svg?style=flat-square)](https://coveralls.io/github/lukasbindreiter/white-brush?branch=developer%2Flukas)  | [Assigned Issues/Todos](https://github.com/lukasbindreiter/white-brush/issues?q=is%3Aopen+assignee%3AShynixn)
| Gabriel   | [![Build Status](https://img.shields.io/travis/meik99/TrafficControl/developer/gabriel.svg?style=flat-square)](https://travis-ci.org/lukasbindreiter/white-brush) |[![Coverage Status](https://img.shields.io/coveralls/lukasbindreiter/white-brush/developer/lukas.svg?style=flat-square)](https://coveralls.io/github/lukasbindreiter/white-brush?branch=developer%2Flukas) |  [Assigned Issues/Todos](https://github.com/lukasbindreiter/white-brush/issues?q=is%3Aopen+assignee%3AShynixn)
| Michael   | [![Build Status](https://img.shields.io/travis/meik99/TrafficControl/developer/michael.svg?style=flat-square)](https://travis-ci.org/lukasbindreiter/white-brush) | [![Coverage Status](https://img.shields.io/coveralls/lukasbindreiter/white-brush/developer/lukas.svg?style=flat-square)](https://coveralls.io/github/lukasbindreiter/white-brush?branch=developer%2Flukas) | [Assigned Issues/Todos](https://github.com/lukasbindreiter/white-brush/issues?q=assignee%3Alukasbindreiter+is%3Aopen) 
| Peter   | [![Build Status](https://img.shields.io/travis/meik99/TrafficControl/developer/peter.svg?style=flat-square)](https://travis-ci.org/lukasbindreiter/white-brush) | [![Coverage Status](https://img.shields.io/coveralls/lukasbindreiter/white-brush/daniel.svg?style=flat-square)](https://coveralls.io/github/lukasbindreiter/white-brush?branch=daniel) | [Assigned Issues/Todos](https://github.com/lukasbindreiter/white-brush/issues?q=assignee%3AEthlaron+is%3Aopen)

## Description

Traffic Control is a system that simulates traffic in a city and tries to prevent traffic jams by collecting data and searching for optimal paths.


## Usage
##### Installing

```bash
git clone https://github.com/meik99/TrafficControl.git
cd TrafficControl
mvn install
```

##### Executing

Windows
```bash
startup.bat
```

Linux
```bash
./startup.sh
```

##### Using the Javascript Web UI

[https://meik99.github.io/TrafficControl](https://meik99.github.io/TrafficControl)

## Contributing / Development
1. Checkout the repository
2. Reimport the TrafficControl maven project and execute 'mvn compile' afterwards.
3. Run unit tests tests and integration tests
```bash
mvn install
```

### Licence

The source code is licensed under the MIT license.