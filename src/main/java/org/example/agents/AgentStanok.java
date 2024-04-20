package org.example.agents;

import OSPABA.*;
import org.example.simulation.*;
import org.example.managers.*;
import org.example.continualAssistants.*;
import org.example.instantAssistants.*;

//meta! id="6"
public class AgentStanok extends Agent
{
	public AgentStanok(int id, Simulation mySim, Agent parent)
	{
		super(id, mySim, parent);
		init();
	}

	@Override
	public void prepareReplication()
	{
		super.prepareReplication();
		// Setup component for the next replication
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
