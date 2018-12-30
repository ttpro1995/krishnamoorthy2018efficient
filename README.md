### Efficient mining of high utility itemsets with multiple minimum utility thresholds

[![Build Status](https://travis-ci.org/ttpro1995/krishnamoorthy2018efficient.svg?branch=master)](https://travis-ci.org/ttpro1995/krishnamoorthy2018efficient)

This is re-implement of algorithm in paper 
```
@article{krishnamoorthy2018efficient,
  title={Efficient mining of high utility itemsets with multiple minimum utility thresholds},
  author={Krishnamoorthy, Srikumar},
  journal={Engineering Applications of Artificial Intelligence},
  volume={69},
  pages={112--126},
  year={2018},
  publisher={Elsevier}
}
```

https://doi.org/10.1016/j.engappai.2017.12.012

How to use 

1. Build 

```
gradle build
gradle fatJar
```

2. Put jar and data in same folder <br>  
* krishnamoorthy2018efficient-all-1.0-SNAPSHOT.jar
* transaction_data.txt
* item_profit_and_minimum_thresholds.txt

3. Run 

```
java -jar krishnamoorthy2018efficient-all-1.0-SNAPSHOT.jar "transaction_data.txt" "item_profit_and_minimum_thresholds.txt"
```


