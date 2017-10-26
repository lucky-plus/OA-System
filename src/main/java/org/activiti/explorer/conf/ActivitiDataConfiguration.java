package org.activiti.explorer.conf;

import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import org.activiti.engine.IdentityService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.User;
import org.activiti.engine.repository.Deployment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ActivitiDataConfiguration {
	protected static final Logger LOGGER = LoggerFactory.getLogger(ActivitiDataConfiguration.class);
	@Autowired
	protected IdentityService identityService;
	@Autowired
	protected RepositoryService repositoryService;

	protected boolean flag = true;

	@PostConstruct
	public void init() {
		if (flag) {
			LOGGER.info("Initializing demo groups");
			initDemoGroups();
			LOGGER.info("Initializing demo users");
			initDemoUsers();
			LOGGER.info("Initializing demo deployments");
			initDemoDeployment();
		}
	}

	protected void initDemoDeployment() {
		String deploymentName = "OA System Deployments";
		List<Deployment> deploymentList = repositoryService.createDeploymentQuery().deploymentName(deploymentName)
				.list();
		if (deploymentList == null || deploymentList.isEmpty()) {
			repositoryService.createDeployment().name(deploymentName)
					.addClasspathResource("realBpmn/egressRequest.bpmn")
					.addClasspathResource("realBpmn/orderDinner.bpmn")
					.deploy();
		}
	}

	protected void initDemoGroups() {
		String[] assignmentGroups = new String[] { "management", "sales", "marketing", "engineering" };
		for (String groupId : assignmentGroups) {
			createGroup(groupId, "assignment");
		}

		String[] securityGroups = new String[] { "user", "admin" };
		for (String groupId : securityGroups) {
			createGroup(groupId, "security-role");
		}
	}

	protected void createGroup(String groupId, String type) {
		if (identityService.createGroupQuery().groupId(groupId).count() == 0) {
			Group newGroup = identityService.newGroup(groupId);
			newGroup.setName(groupId.substring(0, 1).toUpperCase() + groupId.substring(1));
			newGroup.setType(type);
			identityService.saveGroup(newGroup);
		}
	}

	protected void initDemoUsers() {
		createUser("xgd", "x", "The Frog", "123", "kermit@activiti.org", "org/activiti/explorer/images/kermit.jpg",
				Arrays.asList("management", "sales", "marketing", "engineering", "user", "admin"),
				Arrays.asList("birthDate", "10-10-1955", "jobTitle", "Muppet", "location", "Hollywoord", "phone",
						"+123456789", "twitterName", "alfresco", "skype", "activiti_kermit_frog"));

		createUser("fzt", "f", "The Great", "123", "gonzo@activiti.org", "org/activiti/explorer/images/gonzo.jpg",
				Arrays.asList("management", "sales", "marketing", "user"), null);
		createUser("zjk", "z", "Bear", "123", "fozzie@activiti.org", "org/activiti/explorer/images/fozzie.jpg",
				Arrays.asList("marketing", "engineering", "user"), null);
		createUser("dzf", "d", "Bear", "123", "fozzie@activiti.org", "org/activiti/explorer/images/fozzie.jpg",
				Arrays.asList("marketing", "engineering", "user"), null);

	}

	protected void createUser(String userId, String firstName, String lastName, String password, String email,
			String imageResource, List<String> groups, List<String> userInfo) {

		if (identityService.createUserQuery().userId(userId).count() == 0) {

			// Following data can already be set by demo setup script

			User user = identityService.newUser(userId);
			user.setFirstName(firstName);
			user.setLastName(lastName);
			user.setPassword(password);
			user.setEmail(email);
			identityService.saveUser(user);

			if (groups != null) {
				for (String group : groups) {
					identityService.createMembership(userId, group);
				}
			}
		}
	}
}
