package org.example.simulation;

import OSPABA.*;

public class MyMessage extends MessageForm
{
	private double zaciatokCakanieSystem;
	private double koniecCakanieSystem;

	private double zaciatokCakanieStanok;
	private double koniecCakanieStanok;

	private void customInit()
	{
		this.zaciatokCakanieSystem = -1;
		this.koniecCakanieSystem = -1;

		this.zaciatokCakanieStanok = -1;
		this.koniecCakanieStanok = -1;
	}

	public void setZaciatokCakanieSystem(double zaciatokCakanieSystem)
	{
		this.zaciatokCakanieSystem = zaciatokCakanieSystem;
	}

	public void setKoniecCakanieSystem(double koniecCakanieSystem)
	{
		this.koniecCakanieSystem = koniecCakanieSystem;
	}

	public void setZaciatokCakanieStanok(double zaciatokCakanieStanok)
	{
		this.zaciatokCakanieStanok = zaciatokCakanieStanok;
	}

	public void setKoniecCakanieStanok(double koniecCakanieStanok)
	{
		this.koniecCakanieStanok = koniecCakanieStanok;
	}

	public double getCelkoveCakanieSystem()
	{
		if (this.zaciatokCakanieSystem == -1 || this.koniecCakanieSystem == -1)
		{
			throw new RuntimeException("Nie je nastaveny koniec a/alebo zaciatok cakania system!");
		}

		return this.koniecCakanieSystem - this.zaciatokCakanieSystem;
	}

	public double getCelkoveCakanieStanok()
	{
		if (this.zaciatokCakanieStanok == -1 || this.koniecCakanieStanok == -1)
		{
			throw new RuntimeException("Nie je nastaveny koniec a/alebo zaciatok cakania stanok!");
		}

		return this.koniecCakanieStanok - this.zaciatokCakanieStanok;
	}

	public MyMessage(Simulation sim)
	{
		super(sim);

		this.customInit();
	}

	public MyMessage(MyMessage original)
	{
		super(original);
		// copy() is called in superclass

		this.customInit();
	}

	@Override
	public MessageForm createCopy()
	{
		return new MyMessage(this);
	}

	@Override
	protected void copy(MessageForm message)
	{
		super.copy(message);
		MyMessage original = (MyMessage)message;
		// Copy attributes
	}
}
