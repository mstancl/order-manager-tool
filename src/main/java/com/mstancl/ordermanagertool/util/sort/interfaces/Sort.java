package com.mstancl.ordermanagertool.util.sort.interfaces;

import java.util.List;

public interface Sort<T,U> {


    List<T> sort(List<T> items,  Specification<T,U> specification);

}
