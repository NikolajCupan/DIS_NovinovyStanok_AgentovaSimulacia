package org.example.simulation;

import OSPABA.*;

public class MyMessage extends MessageForm
{
	private double zaciatokCakanieStanok;
	private double koniecCakanieStanok;

	private void customInit()
	{
		this.zaciatokCakanieStanok = -1;
		this.koniecCakanieStanok = -1;
	}

	public void setZaciatokCakanieStanok(double zaciatokCakanieStanok)
	{
		this.zaciatokCakanieStanok = zaciatokCakanieStanok;
	}

	public void setKoniecCakanieStanok(double koniecCakanieStanok)
	{
		this.koniecCakanieStanok = koniecCakanieStanok;
	}

	public double getCelkoveCakanieStanok()
	{
		if (this.zaciatokCakanieStanok == -1 || this.koniecCakanieStanok == -1)
		{
			throw new RuntimeException("Nie je nastaveny koniec a/alebo zaciatok cakania!");
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
