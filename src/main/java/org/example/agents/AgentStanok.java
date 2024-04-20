package org.example.agents;

import OSPABA.*;
import OSPDataStruct.SimQueue;
import OSPStat.Stat;
import OSPStat.WStat;
import org.example.simulation.*;
import org.example.managers.*;
import org.example.continualAssistants.*;
import org.example.instantAssistants.*;

//meta! id="6"
public class AgentStanok extends Agent
{
	private SimQueue<MessageForm> frontStanok;
	private Stat statCakanieFrontStanok;
	private boolean stanokObsadeny;

	private void customInit()
	{
		addOwnMessage(Mc.holdObsluhaZakaznika);
	}

	private void customPrepareReplication()
	{
		this.frontStanok = new SimQueue<>(new WStat(this.mySim()));
		this.statCakanieFrontStanok = new Stat();
		this.stanokObsadeny = false;
	}

	public double getPriemerneCakanieFrontStanok()
	{
		return this.statCakanieFrontStanok.mean();
	}

	public void pridajCakanieFrontStanok(MessageForm obsluhaZakaznikaSprava)
	{
		this.statCakanieFrontStanok.addSample(((MyMessage)obsluhaZakaznikaSprava).getCelkoveCakanieStanok());
	}

	public void obsadStanok(MessageForm obsluhaZakaznikaSprava)
	{
		if (this.stanokObsadeny)
		{
			throw new RuntimeException("Stanok je uz obsadeny!");
		}

		((MyMessage)obsluhaZakaznikaSprava).setKoniecCakanieStanok(this.mySim().currentTime());
		this.stanokObsadeny = true;
	}

	public void uvolniStanok()
	{
		if (!this.stanokObsadeny)
		{
			throw new RuntimeException("Stanok nie je obsadeny!");
		}

		this.stanokObsadeny = false;
	}

	public boolean jeFrontPrazdny()
	{
		return this.frontStanok.isEmpty();
	}

	public void pridajFrontStanok(MessageForm prichodZakaznikSprava)
	{
		this.frontStanok.enqueue(prichodZakaznikSprava);
	}

	public MessageForm vyberFrontStanok()
	{
		if (this.frontStanok.isEmpty())
		{
			throw new RuntimeException("Front pred stankom je prazdny!");
		}

		return this.frontStanok.dequeue();
	}

	public boolean getStanokObsadeny()
	{
		return this.stanokObsadeny;
	}

	public AgentStanok(int id, Simulation mySim, Agent parent)
	{
		super(id, mySim, parent);
		init();

		this.customInit();
	}

	@Override
	public void prepareReplication()
	{
		super.prepareReplication();
		// Setup component for the next replication

		this.customPrepareReplication();
	}

	//meta! userInfo="Generated code: do not modify", tag="begin"
	private void init()
	{
		new ManagerStanok(Id.managerStanok, mySim(), this);
		new ProcessObsluhaZakaznika(Id.processObsluhaZakaznika, mySim(), this);
		addOwnMessage(Mc.requestResponseObsluhaZakaznika);
	}
	//meta! tag="end"
}
