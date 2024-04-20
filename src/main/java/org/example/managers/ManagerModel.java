package org.example.managers;

import OSPABA.*;
import org.example.simulation.*;
import org.example.agents.*;
import org.example.continualAssistants.*;
import org.example.instantAssistants.*;
import org.example.vlastne.Prezenter;

//meta! id="1"
public class ManagerModel extends Manager
{
	public ManagerModel(int id, Simulation mySim, Agent myAgent)
	{
		super(id, mySim, myAgent);
		init();
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

	//meta! sender="AgentStanok", id="10", type="Response"
	public void processRequestResponseObsluhaZakaznika(MessageForm message)
	{
    }

	//meta! sender="AgentOkolie", id="9", type="Notice"
	public void processNoticePrichodZakaznika(MessageForm message)
	{
		message.setCode(Mc.requestResponseObsluhaZakaznika);
		message.setAddressee(Id.agentStanok);
		request(message);
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

		case Mc.noticePrichodZakaznika:
			processNoticePrichodZakaznika(message);
		break;

		default:
			processDefault(message);
		break;
		}
	}
	//meta! tag="end"

	@Override
	public AgentModel myAgent()
	{
		return (AgentModel)super.myAgent();
	}

}
