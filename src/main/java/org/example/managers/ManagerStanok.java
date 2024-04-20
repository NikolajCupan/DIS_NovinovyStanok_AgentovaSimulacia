package org.example.managers;

import OSPABA.*;
import org.example.simulation.*;
import org.example.agents.*;

//meta! id="6"
public class ManagerStanok extends Manager
{
	public ManagerStanok(int id, Simulation mySim, Agent myAgent)
	{
		super(id, mySim, myAgent);
		init();
	}

	private void zacniObsluhuZakaznika(MessageForm obsluhaZakaznikaSprava)
	{
		this.myAgent().obsadStanok(obsluhaZakaznikaSprava);
		this.myAgent().pridajCakanieFrontStanok(obsluhaZakaznikaSprava);
		obsluhaZakaznikaSprava.setAddressee(this.myAgent().findAssistant(Id.processObsluhaZakaznika));
		this.startContinualAssistant(obsluhaZakaznikaSprava);
	}

	@Override
	public void prepareReplication()
	{
		super.prepareReplication();
		// Setup component for the next replication

		if (petriNet() != null)
		{
			petriNet().clear();
		}
	}

	//meta! sender="AgentModel", id="10", type="Request"
	public void processRequestResponseObsluhaZakaznika(MessageForm message)
	{
		((MyMessage)message).setZaciatokCakanieStanok(this.mySim().currentTime());

		if (this.myAgent().getStanokObsadeny())
		{
			// Niekto je uz obsluhovany
			this.myAgent().pridajFrontStanok(message);
		}
		else
		{
			// Nikto nie je obsluhovany
			this.zacniObsluhuZakaznika(message);
		}
	}

	//meta! sender="ProcessObsluhaZakaznika", id="15", type="Finish"
	public void processFinish(MessageForm message)
	{
		// Obsluha zakaznika bola ukoncena
		this.myAgent().uvolniStanok();

		// Naplanovanie dalsej obsluhy za podmienky, ze front nie je prazdny
		if (!this.myAgent().jeFrontPrazdny())
		{
			// Front nie je prazdny, naplanovanie obsluhy
			MyMessage obsluhaDalsiehoZakaznikaSprava = (MyMessage)this.myAgent().vyberFrontStanok();
			this.zacniObsluhuZakaznika(obsluhaDalsiehoZakaznikaSprava);
		}

		// Oznamenie ukoncenia obsluhy zakaznika
		message.setCode(Mc.requestResponseObsluhaZakaznika);
		this.response(message);
	}

	//meta! userInfo="Process messages defined in code", id="0"
	public void processDefault(MessageForm message)
	{
		switch (message.code())
		{
		}
	}

	//meta! userInfo="Generated code: do not modify", tag="begin"
	public void init()
	{
	}

	@Override
	public void processMessage(MessageForm message)
	{
		switch (message.code())
		{
		case Mc.requestResponseObsluhaZakaznika:
			processRequestResponseObsluhaZakaznika(message);
		break;

		case Mc.finish:
			processFinish(message);
		break;

		default:
			processDefault(message);
		break;
		}
	}
	//meta! tag="end"

	@Override
	public AgentStanok myAgent()
	{
		return (AgentStanok)super.myAgent();
	}

}
