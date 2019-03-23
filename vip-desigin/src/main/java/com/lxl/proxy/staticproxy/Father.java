package com.lxl.proxy.staticproxy;

import com.lxl.proxy.Person;

public class Father implements Person {
	private Son person;

	public Father(Son person) {
		this.person = person;
	}

	@Override
	public void findLove() {
		System.out.println("父亲物色对象");
		this.person.findLove();
		System.out.println("双方父母同意，确立关系");

	}

	public void findJob() {

	}

}
