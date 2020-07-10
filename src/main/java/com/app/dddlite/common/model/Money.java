package com.app.dddlite.common.model;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Objects;
import java.util.function.Function;


@Embeddable
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Money {

	public static final Money ZERO = Money.wons(0);

	private BigDecimal amount;

	private Money(BigDecimal amount) {
		this.amount = amount;
	}

	public static Money wons(long amount) {
		return new Money(BigDecimal.valueOf(amount));
	}

	public static Money wons(double amount) {
		return new Money(BigDecimal.valueOf(amount));
	}

	public static <T> Money sum(Collection<T> bags, Function<T, Money> monetary) {
		return bags.stream().map(bag -> monetary.apply(bag)).reduce(Money.ZERO, Money::plus);
	}

	public Money plus(Money amount) {
		return new Money(this.amount.add(amount.amount));
	}

	public Money minus(Money amount) {
		return new Money(this.amount.subtract(amount.amount));
	}

	public Money times(double percent) {
		return new Money(this.amount.multiply(BigDecimal.valueOf(percent)));
	}

	public Money divide(double divisor) {
		return new Money(amount.divide(BigDecimal.valueOf(divisor)));
	}

	public boolean isLessThan(Money other) {
		return amount.compareTo(other.amount) < 0;
	}

	public boolean isGreaterThanOrEqual(Money other) {
		return amount.compareTo(other.amount) >= 0;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public Long longValue() {
		return amount.longValue();
	}

	public Double doubleValue() {
		return amount.doubleValue();
	}


	public String toString() {
		return amount.toString() + "원";
	}
}
