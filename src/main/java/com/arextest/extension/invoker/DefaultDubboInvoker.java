package com.arextest.extension.invoker;

import java.util.List;
import java.util.Map;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.rpc.RpcContext;
import org.apache.dubbo.rpc.service.GenericService;

/**
 * @author wildeslam.
 * @create 2023/11/7 15:09
 */
public class DefaultDubboInvoker implements ReplayExtensionInvoker {

  @Override
  public boolean isSupported(String caseType) {
    return InvokerConstants.DUBBO_CASE_TYPE.equalsIgnoreCase(caseType);
  }

  @Override
  public ReplayInvokeResult invoke(ReplayInvocation replayInvocation) {
    ReplayInvokeResult replayInvokeResult = new ReplayInvokeResult();
    try {

      RpcContext.getServiceContext().setAttachments(replayInvocation.get(InvokerConstants.HEADERS, Map.class));

      ReferenceConfig<GenericService> reference = new ReferenceConfig<>();
      reference.setApplication(new ApplicationConfig("defaultDubboInvoker"));
      reference.setUrl(replayInvocation.getUrl());
      reference.setInterface(replayInvocation.get(InvokerConstants.DUBBO_INTERFACE_NAME, String.class));
      reference.setGeneric(true);
      GenericService genericService = reference.get();
      if (genericService == null) {
        return replayInvokeResult;
      }

      Object result = genericService.$invoke(replayInvocation.get(InvokerConstants.DUBBO_METHOD_NAME, String.class),
          (String[]) replayInvocation.get(InvokerConstants.DUBBO_PARAMETER_TYPES, List.class).toArray(new String[0]),
          (replayInvocation.get(InvokerConstants.DUBBO_PARAMETERS, List.class)).toArray());

      replayInvokeResult.setResult(result);
      // add replayId
      replayInvokeResult.setResponseProperties(RpcContext.getServerContext().getAttachments());
    } catch (Exception e) {
      replayInvokeResult.setException(e);
      replayInvokeResult.setErrorMsg(e.getMessage());
    }
    return replayInvokeResult;
  }
}
