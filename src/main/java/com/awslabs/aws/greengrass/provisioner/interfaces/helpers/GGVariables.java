package com.awslabs.aws.greengrass.provisioner.interfaces.helpers;

import com.awslabs.iot.data.GreengrassGroupName;
import com.awslabs.iot.data.PolicyName;
import com.awslabs.iot.data.ThingName;
import com.typesafe.config.Config;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.greengrass.model.FunctionIsolationMode;

public interface GGVariables {
    ThingName getCoreThingName(GreengrassGroupName greengrassGroupName);

    String getCoreDefinitionName(GreengrassGroupName greengrassGroupName);

    PolicyName getCorePolicyName(GreengrassGroupName greengrassGroupName);

    String getDeviceShadowTopicFilterName(ThingName thingName);

    String getGgHost(Region region);

    String getDeviceDefinitionName(GreengrassGroupName greengrassGroupName);

    String getGgdArchiveName(GreengrassGroupName greengrassGroupName);

    String getOemArchiveName(GreengrassGroupName greengrassGroupName);

    String getGgShScriptName(GreengrassGroupName greengrassGroupName);

    String getBaseGgScriptName(GreengrassGroupName greengrassGroupName);

    Config getFunctionDefaults();

    Config getConnectorDefaults();

    FunctionIsolationMode getDefaultFunctionIsolationMode();
}

