package com.believe.documentation;

import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

import com.structurizr.Workspace;
import com.structurizr.api.StructurizrClient;
import com.structurizr.model.Container;
import com.structurizr.model.InteractionStyle;
import com.structurizr.model.Model;
import com.structurizr.model.Person;
import com.structurizr.model.SoftwareSystem;
import com.structurizr.model.Tags;
import com.structurizr.view.ContainerView;
import com.structurizr.view.DynamicView;
import com.structurizr.view.Routing;
import com.structurizr.view.Shape;
import com.structurizr.view.Styles;
import com.structurizr.view.SystemContextView;
import com.structurizr.view.ViewSet;

/**
 * <p> Documentation service for https://structurizr.com/ </p>
 *
 * @author Li Xingping
 */
@Slf4j
@SpringBootApplication
public class DocumentationApplication {
  private static final Long WORKSPACE_ID = 37489L;

  private static final String MICROSERVICE_TAG = "Microservice";
  private static final String MESSAGE_BUS_TAG = "Message Bus";
  private static final String API_GATEWAY_TAG = "Api Gateway";
  private static final String DATASTORE_TAG = "Database";

  public static void main(String[] args) throws Exception {

    SpringApplication app = new SpringApplication(DocumentationApplication.class);
    Environment env = app.run(args).getEnvironment();

    Workspace workspace = new Workspace("Micro starter", "An example of a microservices architecture, which includes asynchronous and parallel behaviour.");
    Model model = workspace.getModel();

    SoftwareSystem mySoftwareSystem = model.addSoftwareSystem("Micro starter Information System", "Stores information ");
    Person user = model.addPerson("User", "A user");

    // ## Ui application ##
    Container uiApplication = mySoftwareSystem.addContainer("UI Application", "Allows users to manage their profile, users", "Vue");

    // ## API Gateway ##
    Container apiGateway = mySoftwareSystem.addContainer("Api Gateway", "All service rest api", "");
    apiGateway.addTags(API_GATEWAY_TAG);

    // ## Command side
    Container usersCommandService = mySoftwareSystem.addContainer("Users Command Service", "The point of access for users mangement - command side.", "Java and Spring Boot");
    usersCommandService.addTags(MICROSERVICE_TAG);
    Container eventStoreDatabase = mySoftwareSystem.addContainer("Event Store", "Stores all events (evensourcing).", "Mongodb");
    eventStoreDatabase.addTags(DATASTORE_TAG);

    //## Query side ##
    Container usersQueryService = mySoftwareSystem.addContainer("Users Query Service", "The point of access for blog materialized views - query side.", "Java and Spring Boot");
    usersQueryService.addTags(MICROSERVICE_TAG);
    Container usersQueryStore = mySoftwareSystem.addContainer("Users Query Store", "Stores information about Users posts - materialized view", "MySQL");
    usersQueryStore.addTags(DATASTORE_TAG);

    // ## Bus ##
    Container messageBus = mySoftwareSystem.addContainer("Message Bus", "Transport for business events.", "RabbitMQ");
    messageBus.addTags(MESSAGE_BUS_TAG);

    // ### Relations ###
    user.uses(mySoftwareSystem, "Uses");
    user.uses(uiApplication, "Uses");
    uiApplication.uses(apiGateway, "Uses");

    // #### users ####
    apiGateway.uses(usersCommandService, "Creates users", "JSON/HTTPS", InteractionStyle.Synchronous);
    usersCommandService.uses(eventStoreDatabase, "Stores users events in", "Mongodb", InteractionStyle.Synchronous);
    eventStoreDatabase.uses(messageBus, "Sends/Tails all events to", "", InteractionStyle.Asynchronous);

    messageBus.uses(usersQueryService, "Sends users events to", "", InteractionStyle.Asynchronous);
    usersQueryService.uses(usersQueryStore, "Stores users data in", "", InteractionStyle.Synchronous);
    apiGateway.uses(usersQueryService, "Read & search users info.", "JSON/HTTPS", InteractionStyle.Synchronous);

    // ## Create views ##
    ViewSet views = workspace.getViews();

    // ### Static context view ###
    SystemContextView contextView = views.createSystemContextView(mySoftwareSystem, "Context", "The System Context diagram for the 'micro-starter' application");
    contextView.addAllElements();

    // ### Static container view ###
    ContainerView containerView = views.createContainerView(mySoftwareSystem, "Containers", null);
    containerView.addAllElements();

    // ### Dynamic view - Create/Update users ###
    DynamicView dynamicViewCreateUsers = views.createDynamicView(mySoftwareSystem, "Create Update users", "This diagram shows what happens when a user users.");
    dynamicViewCreateUsers.add(user, uiApplication);
    dynamicViewCreateUsers.add(uiApplication, apiGateway);
    dynamicViewCreateUsers.add(apiGateway, usersCommandService);
    dynamicViewCreateUsers.add(usersCommandService, eventStoreDatabase);
    dynamicViewCreateUsers.add(eventStoreDatabase, messageBus);

    dynamicViewCreateUsers.add(messageBus, usersQueryService);
    dynamicViewCreateUsers.add(usersQueryService, usersQueryStore);

    // ### Dynamic view - Query users ###
    DynamicView dynamicViewQueryUsers = views.createDynamicView(mySoftwareSystem, "Query Users", "This diagram shows what happens when a user query users.");
    dynamicViewQueryUsers.add(user, uiApplication);
    dynamicViewQueryUsers.add(uiApplication, apiGateway);
    dynamicViewQueryUsers.add(apiGateway, "Consume users rest API", usersQueryService);
    dynamicViewQueryUsers.add(usersQueryService, "Query the user store", usersQueryStore);

    Styles styles = views.getConfiguration().getStyles();
    styles.addElementStyle(Tags.ELEMENT).color("#000000");
    styles.addElementStyle(Tags.PERSON).background("#ffbf00").shape(Shape.Person);
    styles.addElementStyle(Tags.CONTAINER).background("#facc2E");
    styles.addElementStyle(API_GATEWAY_TAG).width(1600).shape(Shape.Pipe);
    styles.addElementStyle(MESSAGE_BUS_TAG).width(1600).shape(Shape.Pipe);
    styles.addElementStyle(MICROSERVICE_TAG).shape(Shape.Hexagon);
    styles.addElementStyle(DATASTORE_TAG).background("#f5da81").shape(Shape.Cylinder);
    styles.addRelationshipStyle(Tags.RELATIONSHIP).routing(Routing.Orthogonal);

    styles.addRelationshipStyle(Tags.ASYNCHRONOUS).dashed(true);
    styles.addRelationshipStyle(Tags.SYNCHRONOUS).dashed(false);

    uploadWorkspaceToStructurizr(workspace, WORKSPACE_ID, env.getProperty("spring.application.structurizr.apikey"), env.getProperty("spring.application.structurizr.apisecret"));
  }

  private static void uploadWorkspaceToStructurizr(Workspace workspace, Long workspaceId, String apiKey, String apiSecret) throws Exception {
    log.info("### Structurizr api Key: " + apiKey);
    log.info("### Structurizr api Secret: " + apiSecret);

    StructurizrClient structurizrClient = new StructurizrClient(apiKey, apiSecret);
    structurizrClient.setMergeFromRemote(true);
    structurizrClient.putWorkspace(workspaceId, workspace);
  }
}
