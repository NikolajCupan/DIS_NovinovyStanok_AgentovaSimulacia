package org.example.agents;

import OSPABA.*;
import org.example.simulation.*;
import org.example.managers.*;
import org.example.continualAssistants.*;
import org.example.instantAssistants.*;

//meta! id="4"
public class AgentOkolie extends Agent
{
	private void customInit()
	{
		addOwnMessage(Mc.holdPrichodZakaznika);
	}

	public AgentOkolie(int id, Simulation mySim, Agent parent)
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
	}

	//meta! userInfo="Generated code: do not modify", tag="begin"
	private void init()
	{
		new ManagerOkolie(Id.managerOkolie, mySim(), this);
		new SchedulerPrichodZakaznika(Id.schedulerPrichodZakaznika, mySim(), this);
		addOwnMessage(Mc.noticeVnutornaPrichodZakaznika);
		addOwnMessage(Mc.noticeInicializaciaSimulacie);
	}
	//meta! tag="end"
}