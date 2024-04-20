package org.example.agents;

import OSPABA.*;
import org.example.simulation.*;
import org.example.managers.*;

//meta! id="1"
public class AgentModel extends Agent
{
	public void odosliInicializacnuSpravu()
	{
		MyMessage inicializacnaSprava = new MyMessage(this.mySim());
		inicializacnaSprava.setCode(Mc.noticeInicializaciaSimulacie);
		inicializacnaSprava.setAddressee(Id.agentOkolie);
		this.manager().notice(inicializacnaSprava);
	}

	public AgentModel(int id, Simulation mySim, Agent parent)
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
		new ManagerModel(Id.managerModel, mySim(), this);
		addOwnMessage(Mc.requestResponseObsluhaZakaznika);
		addOwnMessage(Mc.noticePrichodZakaznika);
	}
	//meta! tag="end"
}