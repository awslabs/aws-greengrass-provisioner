package com.awslabs.aws.greengrass.provisioner.interfaces.helpers;

import com.awslabs.aws.greengrass.provisioner.data.LambdaFunctionArnInfo;
import com.awslabs.aws.greengrass.provisioner.data.ZipFilePathAndFunctionConf;
import com.awslabs.aws.greengrass.provisioner.data.conf.FunctionConf;
import com.awslabs.lambda.data.FunctionName;
import io.vavr.control.Either;
import software.amazon.awssdk.services.iam.model.Role;
import software.amazon.awssdk.services.lambda.model.CreateFunctionResponse;
import software.amazon.awssdk.services.lambda.model.GetFunctionConfigurationResponse;
import software.amazon.awssdk.services.lambda.model.UpdateFunctionConfigurationResponse;

import java.util.Map;

public interface LambdaHelper {
    String GGP_FUNCTION_CONF = "GGP_FUNCTION_CONF";

    ZipFilePathAndFunctionConf buildExecutableFunction(FunctionConf functionConf);

    ZipFilePathAndFunctionConf buildJavaFunction(FunctionConf functionConf);

    ZipFilePathAndFunctionConf buildPython2Function(FunctionConf functionConf);

    ZipFilePathAndFunctionConf buildPython3Function(FunctionConf functionConf);

    ZipFilePathAndFunctionConf buildNodeFunction(FunctionConf functionConf);

    Either<CreateFunctionResponse, UpdateFunctionConfigurationResponse> createOrUpdateFunction(FunctionConf functionConf, Role role, String zipFilePath);

    LambdaFunctionArnInfo publishLambdaFunctionVersion(FunctionName functionName);

    Map<String, String> getFunctionEnvironment(String functionName);

    GetFunctionConfigurationResponse getFunctionConfigurationByName(String functionName);

    void deleteAlias(String functionArn);

    String findFullFunctionArnByPartialName(String substring);
}
