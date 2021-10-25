package tkrzw_rpc;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 * <pre>
 * Definition of the database service.
 * </pre>
 */
@javax.annotation.processing.Generated(
    value = "by gRPC proto compiler",
    comments = "Source: tkrzw_rpc.proto")
public final class DBMServiceGrpc {

  private DBMServiceGrpc() {}

  public static final String SERVICE_NAME = "tkrzw_rpc.DBMService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<tkrzw_rpc.TkrzwRpc.EchoRequest,
      tkrzw_rpc.TkrzwRpc.EchoResponse> getEchoMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Echo",
      requestType = tkrzw_rpc.TkrzwRpc.EchoRequest.class,
      responseType = tkrzw_rpc.TkrzwRpc.EchoResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<tkrzw_rpc.TkrzwRpc.EchoRequest,
      tkrzw_rpc.TkrzwRpc.EchoResponse> getEchoMethod() {
    io.grpc.MethodDescriptor<tkrzw_rpc.TkrzwRpc.EchoRequest, tkrzw_rpc.TkrzwRpc.EchoResponse> getEchoMethod;
    if ((getEchoMethod = DBMServiceGrpc.getEchoMethod) == null) {
      synchronized (DBMServiceGrpc.class) {
        if ((getEchoMethod = DBMServiceGrpc.getEchoMethod) == null) {
          DBMServiceGrpc.getEchoMethod = getEchoMethod =
              io.grpc.MethodDescriptor.<tkrzw_rpc.TkrzwRpc.EchoRequest, tkrzw_rpc.TkrzwRpc.EchoResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Echo"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  tkrzw_rpc.TkrzwRpc.EchoRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  tkrzw_rpc.TkrzwRpc.EchoResponse.getDefaultInstance()))
              .setSchemaDescriptor(new DBMServiceMethodDescriptorSupplier("Echo"))
              .build();
        }
      }
    }
    return getEchoMethod;
  }

  private static volatile io.grpc.MethodDescriptor<tkrzw_rpc.TkrzwRpc.InspectRequest,
      tkrzw_rpc.TkrzwRpc.InspectResponse> getInspectMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Inspect",
      requestType = tkrzw_rpc.TkrzwRpc.InspectRequest.class,
      responseType = tkrzw_rpc.TkrzwRpc.InspectResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<tkrzw_rpc.TkrzwRpc.InspectRequest,
      tkrzw_rpc.TkrzwRpc.InspectResponse> getInspectMethod() {
    io.grpc.MethodDescriptor<tkrzw_rpc.TkrzwRpc.InspectRequest, tkrzw_rpc.TkrzwRpc.InspectResponse> getInspectMethod;
    if ((getInspectMethod = DBMServiceGrpc.getInspectMethod) == null) {
      synchronized (DBMServiceGrpc.class) {
        if ((getInspectMethod = DBMServiceGrpc.getInspectMethod) == null) {
          DBMServiceGrpc.getInspectMethod = getInspectMethod =
              io.grpc.MethodDescriptor.<tkrzw_rpc.TkrzwRpc.InspectRequest, tkrzw_rpc.TkrzwRpc.InspectResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Inspect"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  tkrzw_rpc.TkrzwRpc.InspectRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  tkrzw_rpc.TkrzwRpc.InspectResponse.getDefaultInstance()))
              .setSchemaDescriptor(new DBMServiceMethodDescriptorSupplier("Inspect"))
              .build();
        }
      }
    }
    return getInspectMethod;
  }

  private static volatile io.grpc.MethodDescriptor<tkrzw_rpc.TkrzwRpc.GetRequest,
      tkrzw_rpc.TkrzwRpc.GetResponse> getGetMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Get",
      requestType = tkrzw_rpc.TkrzwRpc.GetRequest.class,
      responseType = tkrzw_rpc.TkrzwRpc.GetResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<tkrzw_rpc.TkrzwRpc.GetRequest,
      tkrzw_rpc.TkrzwRpc.GetResponse> getGetMethod() {
    io.grpc.MethodDescriptor<tkrzw_rpc.TkrzwRpc.GetRequest, tkrzw_rpc.TkrzwRpc.GetResponse> getGetMethod;
    if ((getGetMethod = DBMServiceGrpc.getGetMethod) == null) {
      synchronized (DBMServiceGrpc.class) {
        if ((getGetMethod = DBMServiceGrpc.getGetMethod) == null) {
          DBMServiceGrpc.getGetMethod = getGetMethod =
              io.grpc.MethodDescriptor.<tkrzw_rpc.TkrzwRpc.GetRequest, tkrzw_rpc.TkrzwRpc.GetResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Get"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  tkrzw_rpc.TkrzwRpc.GetRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  tkrzw_rpc.TkrzwRpc.GetResponse.getDefaultInstance()))
              .setSchemaDescriptor(new DBMServiceMethodDescriptorSupplier("Get"))
              .build();
        }
      }
    }
    return getGetMethod;
  }

  private static volatile io.grpc.MethodDescriptor<tkrzw_rpc.TkrzwRpc.GetMultiRequest,
      tkrzw_rpc.TkrzwRpc.GetMultiResponse> getGetMultiMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetMulti",
      requestType = tkrzw_rpc.TkrzwRpc.GetMultiRequest.class,
      responseType = tkrzw_rpc.TkrzwRpc.GetMultiResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<tkrzw_rpc.TkrzwRpc.GetMultiRequest,
      tkrzw_rpc.TkrzwRpc.GetMultiResponse> getGetMultiMethod() {
    io.grpc.MethodDescriptor<tkrzw_rpc.TkrzwRpc.GetMultiRequest, tkrzw_rpc.TkrzwRpc.GetMultiResponse> getGetMultiMethod;
    if ((getGetMultiMethod = DBMServiceGrpc.getGetMultiMethod) == null) {
      synchronized (DBMServiceGrpc.class) {
        if ((getGetMultiMethod = DBMServiceGrpc.getGetMultiMethod) == null) {
          DBMServiceGrpc.getGetMultiMethod = getGetMultiMethod =
              io.grpc.MethodDescriptor.<tkrzw_rpc.TkrzwRpc.GetMultiRequest, tkrzw_rpc.TkrzwRpc.GetMultiResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetMulti"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  tkrzw_rpc.TkrzwRpc.GetMultiRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  tkrzw_rpc.TkrzwRpc.GetMultiResponse.getDefaultInstance()))
              .setSchemaDescriptor(new DBMServiceMethodDescriptorSupplier("GetMulti"))
              .build();
        }
      }
    }
    return getGetMultiMethod;
  }

  private static volatile io.grpc.MethodDescriptor<tkrzw_rpc.TkrzwRpc.SetRequest,
      tkrzw_rpc.TkrzwRpc.SetResponse> getSetMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Set",
      requestType = tkrzw_rpc.TkrzwRpc.SetRequest.class,
      responseType = tkrzw_rpc.TkrzwRpc.SetResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<tkrzw_rpc.TkrzwRpc.SetRequest,
      tkrzw_rpc.TkrzwRpc.SetResponse> getSetMethod() {
    io.grpc.MethodDescriptor<tkrzw_rpc.TkrzwRpc.SetRequest, tkrzw_rpc.TkrzwRpc.SetResponse> getSetMethod;
    if ((getSetMethod = DBMServiceGrpc.getSetMethod) == null) {
      synchronized (DBMServiceGrpc.class) {
        if ((getSetMethod = DBMServiceGrpc.getSetMethod) == null) {
          DBMServiceGrpc.getSetMethod = getSetMethod =
              io.grpc.MethodDescriptor.<tkrzw_rpc.TkrzwRpc.SetRequest, tkrzw_rpc.TkrzwRpc.SetResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Set"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  tkrzw_rpc.TkrzwRpc.SetRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  tkrzw_rpc.TkrzwRpc.SetResponse.getDefaultInstance()))
              .setSchemaDescriptor(new DBMServiceMethodDescriptorSupplier("Set"))
              .build();
        }
      }
    }
    return getSetMethod;
  }

  private static volatile io.grpc.MethodDescriptor<tkrzw_rpc.TkrzwRpc.SetMultiRequest,
      tkrzw_rpc.TkrzwRpc.SetMultiResponse> getSetMultiMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SetMulti",
      requestType = tkrzw_rpc.TkrzwRpc.SetMultiRequest.class,
      responseType = tkrzw_rpc.TkrzwRpc.SetMultiResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<tkrzw_rpc.TkrzwRpc.SetMultiRequest,
      tkrzw_rpc.TkrzwRpc.SetMultiResponse> getSetMultiMethod() {
    io.grpc.MethodDescriptor<tkrzw_rpc.TkrzwRpc.SetMultiRequest, tkrzw_rpc.TkrzwRpc.SetMultiResponse> getSetMultiMethod;
    if ((getSetMultiMethod = DBMServiceGrpc.getSetMultiMethod) == null) {
      synchronized (DBMServiceGrpc.class) {
        if ((getSetMultiMethod = DBMServiceGrpc.getSetMultiMethod) == null) {
          DBMServiceGrpc.getSetMultiMethod = getSetMultiMethod =
              io.grpc.MethodDescriptor.<tkrzw_rpc.TkrzwRpc.SetMultiRequest, tkrzw_rpc.TkrzwRpc.SetMultiResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SetMulti"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  tkrzw_rpc.TkrzwRpc.SetMultiRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  tkrzw_rpc.TkrzwRpc.SetMultiResponse.getDefaultInstance()))
              .setSchemaDescriptor(new DBMServiceMethodDescriptorSupplier("SetMulti"))
              .build();
        }
      }
    }
    return getSetMultiMethod;
  }

  private static volatile io.grpc.MethodDescriptor<tkrzw_rpc.TkrzwRpc.RemoveRequest,
      tkrzw_rpc.TkrzwRpc.RemoveResponse> getRemoveMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Remove",
      requestType = tkrzw_rpc.TkrzwRpc.RemoveRequest.class,
      responseType = tkrzw_rpc.TkrzwRpc.RemoveResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<tkrzw_rpc.TkrzwRpc.RemoveRequest,
      tkrzw_rpc.TkrzwRpc.RemoveResponse> getRemoveMethod() {
    io.grpc.MethodDescriptor<tkrzw_rpc.TkrzwRpc.RemoveRequest, tkrzw_rpc.TkrzwRpc.RemoveResponse> getRemoveMethod;
    if ((getRemoveMethod = DBMServiceGrpc.getRemoveMethod) == null) {
      synchronized (DBMServiceGrpc.class) {
        if ((getRemoveMethod = DBMServiceGrpc.getRemoveMethod) == null) {
          DBMServiceGrpc.getRemoveMethod = getRemoveMethod =
              io.grpc.MethodDescriptor.<tkrzw_rpc.TkrzwRpc.RemoveRequest, tkrzw_rpc.TkrzwRpc.RemoveResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Remove"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  tkrzw_rpc.TkrzwRpc.RemoveRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  tkrzw_rpc.TkrzwRpc.RemoveResponse.getDefaultInstance()))
              .setSchemaDescriptor(new DBMServiceMethodDescriptorSupplier("Remove"))
              .build();
        }
      }
    }
    return getRemoveMethod;
  }

  private static volatile io.grpc.MethodDescriptor<tkrzw_rpc.TkrzwRpc.RemoveMultiRequest,
      tkrzw_rpc.TkrzwRpc.RemoveMultiResponse> getRemoveMultiMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "RemoveMulti",
      requestType = tkrzw_rpc.TkrzwRpc.RemoveMultiRequest.class,
      responseType = tkrzw_rpc.TkrzwRpc.RemoveMultiResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<tkrzw_rpc.TkrzwRpc.RemoveMultiRequest,
      tkrzw_rpc.TkrzwRpc.RemoveMultiResponse> getRemoveMultiMethod() {
    io.grpc.MethodDescriptor<tkrzw_rpc.TkrzwRpc.RemoveMultiRequest, tkrzw_rpc.TkrzwRpc.RemoveMultiResponse> getRemoveMultiMethod;
    if ((getRemoveMultiMethod = DBMServiceGrpc.getRemoveMultiMethod) == null) {
      synchronized (DBMServiceGrpc.class) {
        if ((getRemoveMultiMethod = DBMServiceGrpc.getRemoveMultiMethod) == null) {
          DBMServiceGrpc.getRemoveMultiMethod = getRemoveMultiMethod =
              io.grpc.MethodDescriptor.<tkrzw_rpc.TkrzwRpc.RemoveMultiRequest, tkrzw_rpc.TkrzwRpc.RemoveMultiResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "RemoveMulti"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  tkrzw_rpc.TkrzwRpc.RemoveMultiRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  tkrzw_rpc.TkrzwRpc.RemoveMultiResponse.getDefaultInstance()))
              .setSchemaDescriptor(new DBMServiceMethodDescriptorSupplier("RemoveMulti"))
              .build();
        }
      }
    }
    return getRemoveMultiMethod;
  }

  private static volatile io.grpc.MethodDescriptor<tkrzw_rpc.TkrzwRpc.AppendRequest,
      tkrzw_rpc.TkrzwRpc.AppendResponse> getAppendMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Append",
      requestType = tkrzw_rpc.TkrzwRpc.AppendRequest.class,
      responseType = tkrzw_rpc.TkrzwRpc.AppendResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<tkrzw_rpc.TkrzwRpc.AppendRequest,
      tkrzw_rpc.TkrzwRpc.AppendResponse> getAppendMethod() {
    io.grpc.MethodDescriptor<tkrzw_rpc.TkrzwRpc.AppendRequest, tkrzw_rpc.TkrzwRpc.AppendResponse> getAppendMethod;
    if ((getAppendMethod = DBMServiceGrpc.getAppendMethod) == null) {
      synchronized (DBMServiceGrpc.class) {
        if ((getAppendMethod = DBMServiceGrpc.getAppendMethod) == null) {
          DBMServiceGrpc.getAppendMethod = getAppendMethod =
              io.grpc.MethodDescriptor.<tkrzw_rpc.TkrzwRpc.AppendRequest, tkrzw_rpc.TkrzwRpc.AppendResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Append"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  tkrzw_rpc.TkrzwRpc.AppendRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  tkrzw_rpc.TkrzwRpc.AppendResponse.getDefaultInstance()))
              .setSchemaDescriptor(new DBMServiceMethodDescriptorSupplier("Append"))
              .build();
        }
      }
    }
    return getAppendMethod;
  }

  private static volatile io.grpc.MethodDescriptor<tkrzw_rpc.TkrzwRpc.AppendMultiRequest,
      tkrzw_rpc.TkrzwRpc.AppendMultiResponse> getAppendMultiMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "AppendMulti",
      requestType = tkrzw_rpc.TkrzwRpc.AppendMultiRequest.class,
      responseType = tkrzw_rpc.TkrzwRpc.AppendMultiResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<tkrzw_rpc.TkrzwRpc.AppendMultiRequest,
      tkrzw_rpc.TkrzwRpc.AppendMultiResponse> getAppendMultiMethod() {
    io.grpc.MethodDescriptor<tkrzw_rpc.TkrzwRpc.AppendMultiRequest, tkrzw_rpc.TkrzwRpc.AppendMultiResponse> getAppendMultiMethod;
    if ((getAppendMultiMethod = DBMServiceGrpc.getAppendMultiMethod) == null) {
      synchronized (DBMServiceGrpc.class) {
        if ((getAppendMultiMethod = DBMServiceGrpc.getAppendMultiMethod) == null) {
          DBMServiceGrpc.getAppendMultiMethod = getAppendMultiMethod =
              io.grpc.MethodDescriptor.<tkrzw_rpc.TkrzwRpc.AppendMultiRequest, tkrzw_rpc.TkrzwRpc.AppendMultiResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "AppendMulti"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  tkrzw_rpc.TkrzwRpc.AppendMultiRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  tkrzw_rpc.TkrzwRpc.AppendMultiResponse.getDefaultInstance()))
              .setSchemaDescriptor(new DBMServiceMethodDescriptorSupplier("AppendMulti"))
              .build();
        }
      }
    }
    return getAppendMultiMethod;
  }

  private static volatile io.grpc.MethodDescriptor<tkrzw_rpc.TkrzwRpc.CompareExchangeRequest,
      tkrzw_rpc.TkrzwRpc.CompareExchangeResponse> getCompareExchangeMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CompareExchange",
      requestType = tkrzw_rpc.TkrzwRpc.CompareExchangeRequest.class,
      responseType = tkrzw_rpc.TkrzwRpc.CompareExchangeResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<tkrzw_rpc.TkrzwRpc.CompareExchangeRequest,
      tkrzw_rpc.TkrzwRpc.CompareExchangeResponse> getCompareExchangeMethod() {
    io.grpc.MethodDescriptor<tkrzw_rpc.TkrzwRpc.CompareExchangeRequest, tkrzw_rpc.TkrzwRpc.CompareExchangeResponse> getCompareExchangeMethod;
    if ((getCompareExchangeMethod = DBMServiceGrpc.getCompareExchangeMethod) == null) {
      synchronized (DBMServiceGrpc.class) {
        if ((getCompareExchangeMethod = DBMServiceGrpc.getCompareExchangeMethod) == null) {
          DBMServiceGrpc.getCompareExchangeMethod = getCompareExchangeMethod =
              io.grpc.MethodDescriptor.<tkrzw_rpc.TkrzwRpc.CompareExchangeRequest, tkrzw_rpc.TkrzwRpc.CompareExchangeResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CompareExchange"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  tkrzw_rpc.TkrzwRpc.CompareExchangeRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  tkrzw_rpc.TkrzwRpc.CompareExchangeResponse.getDefaultInstance()))
              .setSchemaDescriptor(new DBMServiceMethodDescriptorSupplier("CompareExchange"))
              .build();
        }
      }
    }
    return getCompareExchangeMethod;
  }

  private static volatile io.grpc.MethodDescriptor<tkrzw_rpc.TkrzwRpc.IncrementRequest,
      tkrzw_rpc.TkrzwRpc.IncrementResponse> getIncrementMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Increment",
      requestType = tkrzw_rpc.TkrzwRpc.IncrementRequest.class,
      responseType = tkrzw_rpc.TkrzwRpc.IncrementResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<tkrzw_rpc.TkrzwRpc.IncrementRequest,
      tkrzw_rpc.TkrzwRpc.IncrementResponse> getIncrementMethod() {
    io.grpc.MethodDescriptor<tkrzw_rpc.TkrzwRpc.IncrementRequest, tkrzw_rpc.TkrzwRpc.IncrementResponse> getIncrementMethod;
    if ((getIncrementMethod = DBMServiceGrpc.getIncrementMethod) == null) {
      synchronized (DBMServiceGrpc.class) {
        if ((getIncrementMethod = DBMServiceGrpc.getIncrementMethod) == null) {
          DBMServiceGrpc.getIncrementMethod = getIncrementMethod =
              io.grpc.MethodDescriptor.<tkrzw_rpc.TkrzwRpc.IncrementRequest, tkrzw_rpc.TkrzwRpc.IncrementResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Increment"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  tkrzw_rpc.TkrzwRpc.IncrementRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  tkrzw_rpc.TkrzwRpc.IncrementResponse.getDefaultInstance()))
              .setSchemaDescriptor(new DBMServiceMethodDescriptorSupplier("Increment"))
              .build();
        }
      }
    }
    return getIncrementMethod;
  }

  private static volatile io.grpc.MethodDescriptor<tkrzw_rpc.TkrzwRpc.CompareExchangeMultiRequest,
      tkrzw_rpc.TkrzwRpc.CompareExchangeMultiResponse> getCompareExchangeMultiMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CompareExchangeMulti",
      requestType = tkrzw_rpc.TkrzwRpc.CompareExchangeMultiRequest.class,
      responseType = tkrzw_rpc.TkrzwRpc.CompareExchangeMultiResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<tkrzw_rpc.TkrzwRpc.CompareExchangeMultiRequest,
      tkrzw_rpc.TkrzwRpc.CompareExchangeMultiResponse> getCompareExchangeMultiMethod() {
    io.grpc.MethodDescriptor<tkrzw_rpc.TkrzwRpc.CompareExchangeMultiRequest, tkrzw_rpc.TkrzwRpc.CompareExchangeMultiResponse> getCompareExchangeMultiMethod;
    if ((getCompareExchangeMultiMethod = DBMServiceGrpc.getCompareExchangeMultiMethod) == null) {
      synchronized (DBMServiceGrpc.class) {
        if ((getCompareExchangeMultiMethod = DBMServiceGrpc.getCompareExchangeMultiMethod) == null) {
          DBMServiceGrpc.getCompareExchangeMultiMethod = getCompareExchangeMultiMethod =
              io.grpc.MethodDescriptor.<tkrzw_rpc.TkrzwRpc.CompareExchangeMultiRequest, tkrzw_rpc.TkrzwRpc.CompareExchangeMultiResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CompareExchangeMulti"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  tkrzw_rpc.TkrzwRpc.CompareExchangeMultiRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  tkrzw_rpc.TkrzwRpc.CompareExchangeMultiResponse.getDefaultInstance()))
              .setSchemaDescriptor(new DBMServiceMethodDescriptorSupplier("CompareExchangeMulti"))
              .build();
        }
      }
    }
    return getCompareExchangeMultiMethod;
  }

  private static volatile io.grpc.MethodDescriptor<tkrzw_rpc.TkrzwRpc.RekeyRequest,
      tkrzw_rpc.TkrzwRpc.RekeyResponse> getRekeyMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Rekey",
      requestType = tkrzw_rpc.TkrzwRpc.RekeyRequest.class,
      responseType = tkrzw_rpc.TkrzwRpc.RekeyResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<tkrzw_rpc.TkrzwRpc.RekeyRequest,
      tkrzw_rpc.TkrzwRpc.RekeyResponse> getRekeyMethod() {
    io.grpc.MethodDescriptor<tkrzw_rpc.TkrzwRpc.RekeyRequest, tkrzw_rpc.TkrzwRpc.RekeyResponse> getRekeyMethod;
    if ((getRekeyMethod = DBMServiceGrpc.getRekeyMethod) == null) {
      synchronized (DBMServiceGrpc.class) {
        if ((getRekeyMethod = DBMServiceGrpc.getRekeyMethod) == null) {
          DBMServiceGrpc.getRekeyMethod = getRekeyMethod =
              io.grpc.MethodDescriptor.<tkrzw_rpc.TkrzwRpc.RekeyRequest, tkrzw_rpc.TkrzwRpc.RekeyResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Rekey"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  tkrzw_rpc.TkrzwRpc.RekeyRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  tkrzw_rpc.TkrzwRpc.RekeyResponse.getDefaultInstance()))
              .setSchemaDescriptor(new DBMServiceMethodDescriptorSupplier("Rekey"))
              .build();
        }
      }
    }
    return getRekeyMethod;
  }

  private static volatile io.grpc.MethodDescriptor<tkrzw_rpc.TkrzwRpc.PopFirstRequest,
      tkrzw_rpc.TkrzwRpc.PopFirstResponse> getPopFirstMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "PopFirst",
      requestType = tkrzw_rpc.TkrzwRpc.PopFirstRequest.class,
      responseType = tkrzw_rpc.TkrzwRpc.PopFirstResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<tkrzw_rpc.TkrzwRpc.PopFirstRequest,
      tkrzw_rpc.TkrzwRpc.PopFirstResponse> getPopFirstMethod() {
    io.grpc.MethodDescriptor<tkrzw_rpc.TkrzwRpc.PopFirstRequest, tkrzw_rpc.TkrzwRpc.PopFirstResponse> getPopFirstMethod;
    if ((getPopFirstMethod = DBMServiceGrpc.getPopFirstMethod) == null) {
      synchronized (DBMServiceGrpc.class) {
        if ((getPopFirstMethod = DBMServiceGrpc.getPopFirstMethod) == null) {
          DBMServiceGrpc.getPopFirstMethod = getPopFirstMethod =
              io.grpc.MethodDescriptor.<tkrzw_rpc.TkrzwRpc.PopFirstRequest, tkrzw_rpc.TkrzwRpc.PopFirstResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "PopFirst"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  tkrzw_rpc.TkrzwRpc.PopFirstRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  tkrzw_rpc.TkrzwRpc.PopFirstResponse.getDefaultInstance()))
              .setSchemaDescriptor(new DBMServiceMethodDescriptorSupplier("PopFirst"))
              .build();
        }
      }
    }
    return getPopFirstMethod;
  }

  private static volatile io.grpc.MethodDescriptor<tkrzw_rpc.TkrzwRpc.PushLastRequest,
      tkrzw_rpc.TkrzwRpc.PushLastResponse> getPushLastMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "PushLast",
      requestType = tkrzw_rpc.TkrzwRpc.PushLastRequest.class,
      responseType = tkrzw_rpc.TkrzwRpc.PushLastResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<tkrzw_rpc.TkrzwRpc.PushLastRequest,
      tkrzw_rpc.TkrzwRpc.PushLastResponse> getPushLastMethod() {
    io.grpc.MethodDescriptor<tkrzw_rpc.TkrzwRpc.PushLastRequest, tkrzw_rpc.TkrzwRpc.PushLastResponse> getPushLastMethod;
    if ((getPushLastMethod = DBMServiceGrpc.getPushLastMethod) == null) {
      synchronized (DBMServiceGrpc.class) {
        if ((getPushLastMethod = DBMServiceGrpc.getPushLastMethod) == null) {
          DBMServiceGrpc.getPushLastMethod = getPushLastMethod =
              io.grpc.MethodDescriptor.<tkrzw_rpc.TkrzwRpc.PushLastRequest, tkrzw_rpc.TkrzwRpc.PushLastResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "PushLast"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  tkrzw_rpc.TkrzwRpc.PushLastRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  tkrzw_rpc.TkrzwRpc.PushLastResponse.getDefaultInstance()))
              .setSchemaDescriptor(new DBMServiceMethodDescriptorSupplier("PushLast"))
              .build();
        }
      }
    }
    return getPushLastMethod;
  }

  private static volatile io.grpc.MethodDescriptor<tkrzw_rpc.TkrzwRpc.CountRequest,
      tkrzw_rpc.TkrzwRpc.CountResponse> getCountMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Count",
      requestType = tkrzw_rpc.TkrzwRpc.CountRequest.class,
      responseType = tkrzw_rpc.TkrzwRpc.CountResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<tkrzw_rpc.TkrzwRpc.CountRequest,
      tkrzw_rpc.TkrzwRpc.CountResponse> getCountMethod() {
    io.grpc.MethodDescriptor<tkrzw_rpc.TkrzwRpc.CountRequest, tkrzw_rpc.TkrzwRpc.CountResponse> getCountMethod;
    if ((getCountMethod = DBMServiceGrpc.getCountMethod) == null) {
      synchronized (DBMServiceGrpc.class) {
        if ((getCountMethod = DBMServiceGrpc.getCountMethod) == null) {
          DBMServiceGrpc.getCountMethod = getCountMethod =
              io.grpc.MethodDescriptor.<tkrzw_rpc.TkrzwRpc.CountRequest, tkrzw_rpc.TkrzwRpc.CountResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Count"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  tkrzw_rpc.TkrzwRpc.CountRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  tkrzw_rpc.TkrzwRpc.CountResponse.getDefaultInstance()))
              .setSchemaDescriptor(new DBMServiceMethodDescriptorSupplier("Count"))
              .build();
        }
      }
    }
    return getCountMethod;
  }

  private static volatile io.grpc.MethodDescriptor<tkrzw_rpc.TkrzwRpc.GetFileSizeRequest,
      tkrzw_rpc.TkrzwRpc.GetFileSizeResponse> getGetFileSizeMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetFileSize",
      requestType = tkrzw_rpc.TkrzwRpc.GetFileSizeRequest.class,
      responseType = tkrzw_rpc.TkrzwRpc.GetFileSizeResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<tkrzw_rpc.TkrzwRpc.GetFileSizeRequest,
      tkrzw_rpc.TkrzwRpc.GetFileSizeResponse> getGetFileSizeMethod() {
    io.grpc.MethodDescriptor<tkrzw_rpc.TkrzwRpc.GetFileSizeRequest, tkrzw_rpc.TkrzwRpc.GetFileSizeResponse> getGetFileSizeMethod;
    if ((getGetFileSizeMethod = DBMServiceGrpc.getGetFileSizeMethod) == null) {
      synchronized (DBMServiceGrpc.class) {
        if ((getGetFileSizeMethod = DBMServiceGrpc.getGetFileSizeMethod) == null) {
          DBMServiceGrpc.getGetFileSizeMethod = getGetFileSizeMethod =
              io.grpc.MethodDescriptor.<tkrzw_rpc.TkrzwRpc.GetFileSizeRequest, tkrzw_rpc.TkrzwRpc.GetFileSizeResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetFileSize"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  tkrzw_rpc.TkrzwRpc.GetFileSizeRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  tkrzw_rpc.TkrzwRpc.GetFileSizeResponse.getDefaultInstance()))
              .setSchemaDescriptor(new DBMServiceMethodDescriptorSupplier("GetFileSize"))
              .build();
        }
      }
    }
    return getGetFileSizeMethod;
  }

  private static volatile io.grpc.MethodDescriptor<tkrzw_rpc.TkrzwRpc.ClearRequest,
      tkrzw_rpc.TkrzwRpc.ClearResponse> getClearMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Clear",
      requestType = tkrzw_rpc.TkrzwRpc.ClearRequest.class,
      responseType = tkrzw_rpc.TkrzwRpc.ClearResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<tkrzw_rpc.TkrzwRpc.ClearRequest,
      tkrzw_rpc.TkrzwRpc.ClearResponse> getClearMethod() {
    io.grpc.MethodDescriptor<tkrzw_rpc.TkrzwRpc.ClearRequest, tkrzw_rpc.TkrzwRpc.ClearResponse> getClearMethod;
    if ((getClearMethod = DBMServiceGrpc.getClearMethod) == null) {
      synchronized (DBMServiceGrpc.class) {
        if ((getClearMethod = DBMServiceGrpc.getClearMethod) == null) {
          DBMServiceGrpc.getClearMethod = getClearMethod =
              io.grpc.MethodDescriptor.<tkrzw_rpc.TkrzwRpc.ClearRequest, tkrzw_rpc.TkrzwRpc.ClearResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Clear"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  tkrzw_rpc.TkrzwRpc.ClearRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  tkrzw_rpc.TkrzwRpc.ClearResponse.getDefaultInstance()))
              .setSchemaDescriptor(new DBMServiceMethodDescriptorSupplier("Clear"))
              .build();
        }
      }
    }
    return getClearMethod;
  }

  private static volatile io.grpc.MethodDescriptor<tkrzw_rpc.TkrzwRpc.RebuildRequest,
      tkrzw_rpc.TkrzwRpc.RebuildResponse> getRebuildMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Rebuild",
      requestType = tkrzw_rpc.TkrzwRpc.RebuildRequest.class,
      responseType = tkrzw_rpc.TkrzwRpc.RebuildResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<tkrzw_rpc.TkrzwRpc.RebuildRequest,
      tkrzw_rpc.TkrzwRpc.RebuildResponse> getRebuildMethod() {
    io.grpc.MethodDescriptor<tkrzw_rpc.TkrzwRpc.RebuildRequest, tkrzw_rpc.TkrzwRpc.RebuildResponse> getRebuildMethod;
    if ((getRebuildMethod = DBMServiceGrpc.getRebuildMethod) == null) {
      synchronized (DBMServiceGrpc.class) {
        if ((getRebuildMethod = DBMServiceGrpc.getRebuildMethod) == null) {
          DBMServiceGrpc.getRebuildMethod = getRebuildMethod =
              io.grpc.MethodDescriptor.<tkrzw_rpc.TkrzwRpc.RebuildRequest, tkrzw_rpc.TkrzwRpc.RebuildResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Rebuild"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  tkrzw_rpc.TkrzwRpc.RebuildRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  tkrzw_rpc.TkrzwRpc.RebuildResponse.getDefaultInstance()))
              .setSchemaDescriptor(new DBMServiceMethodDescriptorSupplier("Rebuild"))
              .build();
        }
      }
    }
    return getRebuildMethod;
  }

  private static volatile io.grpc.MethodDescriptor<tkrzw_rpc.TkrzwRpc.ShouldBeRebuiltRequest,
      tkrzw_rpc.TkrzwRpc.ShouldBeRebuiltResponse> getShouldBeRebuiltMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ShouldBeRebuilt",
      requestType = tkrzw_rpc.TkrzwRpc.ShouldBeRebuiltRequest.class,
      responseType = tkrzw_rpc.TkrzwRpc.ShouldBeRebuiltResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<tkrzw_rpc.TkrzwRpc.ShouldBeRebuiltRequest,
      tkrzw_rpc.TkrzwRpc.ShouldBeRebuiltResponse> getShouldBeRebuiltMethod() {
    io.grpc.MethodDescriptor<tkrzw_rpc.TkrzwRpc.ShouldBeRebuiltRequest, tkrzw_rpc.TkrzwRpc.ShouldBeRebuiltResponse> getShouldBeRebuiltMethod;
    if ((getShouldBeRebuiltMethod = DBMServiceGrpc.getShouldBeRebuiltMethod) == null) {
      synchronized (DBMServiceGrpc.class) {
        if ((getShouldBeRebuiltMethod = DBMServiceGrpc.getShouldBeRebuiltMethod) == null) {
          DBMServiceGrpc.getShouldBeRebuiltMethod = getShouldBeRebuiltMethod =
              io.grpc.MethodDescriptor.<tkrzw_rpc.TkrzwRpc.ShouldBeRebuiltRequest, tkrzw_rpc.TkrzwRpc.ShouldBeRebuiltResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ShouldBeRebuilt"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  tkrzw_rpc.TkrzwRpc.ShouldBeRebuiltRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  tkrzw_rpc.TkrzwRpc.ShouldBeRebuiltResponse.getDefaultInstance()))
              .setSchemaDescriptor(new DBMServiceMethodDescriptorSupplier("ShouldBeRebuilt"))
              .build();
        }
      }
    }
    return getShouldBeRebuiltMethod;
  }

  private static volatile io.grpc.MethodDescriptor<tkrzw_rpc.TkrzwRpc.SynchronizeRequest,
      tkrzw_rpc.TkrzwRpc.SynchronizeResponse> getSynchronizeMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Synchronize",
      requestType = tkrzw_rpc.TkrzwRpc.SynchronizeRequest.class,
      responseType = tkrzw_rpc.TkrzwRpc.SynchronizeResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<tkrzw_rpc.TkrzwRpc.SynchronizeRequest,
      tkrzw_rpc.TkrzwRpc.SynchronizeResponse> getSynchronizeMethod() {
    io.grpc.MethodDescriptor<tkrzw_rpc.TkrzwRpc.SynchronizeRequest, tkrzw_rpc.TkrzwRpc.SynchronizeResponse> getSynchronizeMethod;
    if ((getSynchronizeMethod = DBMServiceGrpc.getSynchronizeMethod) == null) {
      synchronized (DBMServiceGrpc.class) {
        if ((getSynchronizeMethod = DBMServiceGrpc.getSynchronizeMethod) == null) {
          DBMServiceGrpc.getSynchronizeMethod = getSynchronizeMethod =
              io.grpc.MethodDescriptor.<tkrzw_rpc.TkrzwRpc.SynchronizeRequest, tkrzw_rpc.TkrzwRpc.SynchronizeResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Synchronize"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  tkrzw_rpc.TkrzwRpc.SynchronizeRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  tkrzw_rpc.TkrzwRpc.SynchronizeResponse.getDefaultInstance()))
              .setSchemaDescriptor(new DBMServiceMethodDescriptorSupplier("Synchronize"))
              .build();
        }
      }
    }
    return getSynchronizeMethod;
  }

  private static volatile io.grpc.MethodDescriptor<tkrzw_rpc.TkrzwRpc.SearchRequest,
      tkrzw_rpc.TkrzwRpc.SearchResponse> getSearchMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Search",
      requestType = tkrzw_rpc.TkrzwRpc.SearchRequest.class,
      responseType = tkrzw_rpc.TkrzwRpc.SearchResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<tkrzw_rpc.TkrzwRpc.SearchRequest,
      tkrzw_rpc.TkrzwRpc.SearchResponse> getSearchMethod() {
    io.grpc.MethodDescriptor<tkrzw_rpc.TkrzwRpc.SearchRequest, tkrzw_rpc.TkrzwRpc.SearchResponse> getSearchMethod;
    if ((getSearchMethod = DBMServiceGrpc.getSearchMethod) == null) {
      synchronized (DBMServiceGrpc.class) {
        if ((getSearchMethod = DBMServiceGrpc.getSearchMethod) == null) {
          DBMServiceGrpc.getSearchMethod = getSearchMethod =
              io.grpc.MethodDescriptor.<tkrzw_rpc.TkrzwRpc.SearchRequest, tkrzw_rpc.TkrzwRpc.SearchResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Search"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  tkrzw_rpc.TkrzwRpc.SearchRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  tkrzw_rpc.TkrzwRpc.SearchResponse.getDefaultInstance()))
              .setSchemaDescriptor(new DBMServiceMethodDescriptorSupplier("Search"))
              .build();
        }
      }
    }
    return getSearchMethod;
  }

  private static volatile io.grpc.MethodDescriptor<tkrzw_rpc.TkrzwRpc.StreamRequest,
      tkrzw_rpc.TkrzwRpc.StreamResponse> getStreamMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Stream",
      requestType = tkrzw_rpc.TkrzwRpc.StreamRequest.class,
      responseType = tkrzw_rpc.TkrzwRpc.StreamResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
  public static io.grpc.MethodDescriptor<tkrzw_rpc.TkrzwRpc.StreamRequest,
      tkrzw_rpc.TkrzwRpc.StreamResponse> getStreamMethod() {
    io.grpc.MethodDescriptor<tkrzw_rpc.TkrzwRpc.StreamRequest, tkrzw_rpc.TkrzwRpc.StreamResponse> getStreamMethod;
    if ((getStreamMethod = DBMServiceGrpc.getStreamMethod) == null) {
      synchronized (DBMServiceGrpc.class) {
        if ((getStreamMethod = DBMServiceGrpc.getStreamMethod) == null) {
          DBMServiceGrpc.getStreamMethod = getStreamMethod =
              io.grpc.MethodDescriptor.<tkrzw_rpc.TkrzwRpc.StreamRequest, tkrzw_rpc.TkrzwRpc.StreamResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Stream"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  tkrzw_rpc.TkrzwRpc.StreamRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  tkrzw_rpc.TkrzwRpc.StreamResponse.getDefaultInstance()))
              .setSchemaDescriptor(new DBMServiceMethodDescriptorSupplier("Stream"))
              .build();
        }
      }
    }
    return getStreamMethod;
  }

  private static volatile io.grpc.MethodDescriptor<tkrzw_rpc.TkrzwRpc.IterateRequest,
      tkrzw_rpc.TkrzwRpc.IterateResponse> getIterateMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Iterate",
      requestType = tkrzw_rpc.TkrzwRpc.IterateRequest.class,
      responseType = tkrzw_rpc.TkrzwRpc.IterateResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
  public static io.grpc.MethodDescriptor<tkrzw_rpc.TkrzwRpc.IterateRequest,
      tkrzw_rpc.TkrzwRpc.IterateResponse> getIterateMethod() {
    io.grpc.MethodDescriptor<tkrzw_rpc.TkrzwRpc.IterateRequest, tkrzw_rpc.TkrzwRpc.IterateResponse> getIterateMethod;
    if ((getIterateMethod = DBMServiceGrpc.getIterateMethod) == null) {
      synchronized (DBMServiceGrpc.class) {
        if ((getIterateMethod = DBMServiceGrpc.getIterateMethod) == null) {
          DBMServiceGrpc.getIterateMethod = getIterateMethod =
              io.grpc.MethodDescriptor.<tkrzw_rpc.TkrzwRpc.IterateRequest, tkrzw_rpc.TkrzwRpc.IterateResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Iterate"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  tkrzw_rpc.TkrzwRpc.IterateRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  tkrzw_rpc.TkrzwRpc.IterateResponse.getDefaultInstance()))
              .setSchemaDescriptor(new DBMServiceMethodDescriptorSupplier("Iterate"))
              .build();
        }
      }
    }
    return getIterateMethod;
  }

  private static volatile io.grpc.MethodDescriptor<tkrzw_rpc.TkrzwRpc.ReplicateRequest,
      tkrzw_rpc.TkrzwRpc.ReplicateResponse> getReplicateMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Replicate",
      requestType = tkrzw_rpc.TkrzwRpc.ReplicateRequest.class,
      responseType = tkrzw_rpc.TkrzwRpc.ReplicateResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<tkrzw_rpc.TkrzwRpc.ReplicateRequest,
      tkrzw_rpc.TkrzwRpc.ReplicateResponse> getReplicateMethod() {
    io.grpc.MethodDescriptor<tkrzw_rpc.TkrzwRpc.ReplicateRequest, tkrzw_rpc.TkrzwRpc.ReplicateResponse> getReplicateMethod;
    if ((getReplicateMethod = DBMServiceGrpc.getReplicateMethod) == null) {
      synchronized (DBMServiceGrpc.class) {
        if ((getReplicateMethod = DBMServiceGrpc.getReplicateMethod) == null) {
          DBMServiceGrpc.getReplicateMethod = getReplicateMethod =
              io.grpc.MethodDescriptor.<tkrzw_rpc.TkrzwRpc.ReplicateRequest, tkrzw_rpc.TkrzwRpc.ReplicateResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Replicate"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  tkrzw_rpc.TkrzwRpc.ReplicateRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  tkrzw_rpc.TkrzwRpc.ReplicateResponse.getDefaultInstance()))
              .setSchemaDescriptor(new DBMServiceMethodDescriptorSupplier("Replicate"))
              .build();
        }
      }
    }
    return getReplicateMethod;
  }

  private static volatile io.grpc.MethodDescriptor<tkrzw_rpc.TkrzwRpc.ChangeMasterRequest,
      tkrzw_rpc.TkrzwRpc.ChangeMasterResponse> getChangeMasterMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ChangeMaster",
      requestType = tkrzw_rpc.TkrzwRpc.ChangeMasterRequest.class,
      responseType = tkrzw_rpc.TkrzwRpc.ChangeMasterResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<tkrzw_rpc.TkrzwRpc.ChangeMasterRequest,
      tkrzw_rpc.TkrzwRpc.ChangeMasterResponse> getChangeMasterMethod() {
    io.grpc.MethodDescriptor<tkrzw_rpc.TkrzwRpc.ChangeMasterRequest, tkrzw_rpc.TkrzwRpc.ChangeMasterResponse> getChangeMasterMethod;
    if ((getChangeMasterMethod = DBMServiceGrpc.getChangeMasterMethod) == null) {
      synchronized (DBMServiceGrpc.class) {
        if ((getChangeMasterMethod = DBMServiceGrpc.getChangeMasterMethod) == null) {
          DBMServiceGrpc.getChangeMasterMethod = getChangeMasterMethod =
              io.grpc.MethodDescriptor.<tkrzw_rpc.TkrzwRpc.ChangeMasterRequest, tkrzw_rpc.TkrzwRpc.ChangeMasterResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ChangeMaster"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  tkrzw_rpc.TkrzwRpc.ChangeMasterRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  tkrzw_rpc.TkrzwRpc.ChangeMasterResponse.getDefaultInstance()))
              .setSchemaDescriptor(new DBMServiceMethodDescriptorSupplier("ChangeMaster"))
              .build();
        }
      }
    }
    return getChangeMasterMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static DBMServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<DBMServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<DBMServiceStub>() {
        @java.lang.Override
        public DBMServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new DBMServiceStub(channel, callOptions);
        }
      };
    return DBMServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static DBMServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<DBMServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<DBMServiceBlockingStub>() {
        @java.lang.Override
        public DBMServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new DBMServiceBlockingStub(channel, callOptions);
        }
      };
    return DBMServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static DBMServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<DBMServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<DBMServiceFutureStub>() {
        @java.lang.Override
        public DBMServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new DBMServiceFutureStub(channel, callOptions);
        }
      };
    return DBMServiceFutureStub.newStub(factory, channel);
  }

  /**
   * <pre>
   * Definition of the database service.
   * </pre>
   */
  public static abstract class DBMServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void echo(tkrzw_rpc.TkrzwRpc.EchoRequest request,
        io.grpc.stub.StreamObserver<tkrzw_rpc.TkrzwRpc.EchoResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getEchoMethod(), responseObserver);
    }

    /**
     */
    public void inspect(tkrzw_rpc.TkrzwRpc.InspectRequest request,
        io.grpc.stub.StreamObserver<tkrzw_rpc.TkrzwRpc.InspectResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getInspectMethod(), responseObserver);
    }

    /**
     */
    public void get(tkrzw_rpc.TkrzwRpc.GetRequest request,
        io.grpc.stub.StreamObserver<tkrzw_rpc.TkrzwRpc.GetResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getGetMethod(), responseObserver);
    }

    /**
     */
    public void getMulti(tkrzw_rpc.TkrzwRpc.GetMultiRequest request,
        io.grpc.stub.StreamObserver<tkrzw_rpc.TkrzwRpc.GetMultiResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getGetMultiMethod(), responseObserver);
    }

    /**
     */
    public void set(tkrzw_rpc.TkrzwRpc.SetRequest request,
        io.grpc.stub.StreamObserver<tkrzw_rpc.TkrzwRpc.SetResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getSetMethod(), responseObserver);
    }

    /**
     */
    public void setMulti(tkrzw_rpc.TkrzwRpc.SetMultiRequest request,
        io.grpc.stub.StreamObserver<tkrzw_rpc.TkrzwRpc.SetMultiResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getSetMultiMethod(), responseObserver);
    }

    /**
     */
    public void remove(tkrzw_rpc.TkrzwRpc.RemoveRequest request,
        io.grpc.stub.StreamObserver<tkrzw_rpc.TkrzwRpc.RemoveResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getRemoveMethod(), responseObserver);
    }

    /**
     */
    public void removeMulti(tkrzw_rpc.TkrzwRpc.RemoveMultiRequest request,
        io.grpc.stub.StreamObserver<tkrzw_rpc.TkrzwRpc.RemoveMultiResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getRemoveMultiMethod(), responseObserver);
    }

    /**
     */
    public void append(tkrzw_rpc.TkrzwRpc.AppendRequest request,
        io.grpc.stub.StreamObserver<tkrzw_rpc.TkrzwRpc.AppendResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getAppendMethod(), responseObserver);
    }

    /**
     */
    public void appendMulti(tkrzw_rpc.TkrzwRpc.AppendMultiRequest request,
        io.grpc.stub.StreamObserver<tkrzw_rpc.TkrzwRpc.AppendMultiResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getAppendMultiMethod(), responseObserver);
    }

    /**
     */
    public void compareExchange(tkrzw_rpc.TkrzwRpc.CompareExchangeRequest request,
        io.grpc.stub.StreamObserver<tkrzw_rpc.TkrzwRpc.CompareExchangeResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getCompareExchangeMethod(), responseObserver);
    }

    /**
     */
    public void increment(tkrzw_rpc.TkrzwRpc.IncrementRequest request,
        io.grpc.stub.StreamObserver<tkrzw_rpc.TkrzwRpc.IncrementResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getIncrementMethod(), responseObserver);
    }

    /**
     */
    public void compareExchangeMulti(tkrzw_rpc.TkrzwRpc.CompareExchangeMultiRequest request,
        io.grpc.stub.StreamObserver<tkrzw_rpc.TkrzwRpc.CompareExchangeMultiResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getCompareExchangeMultiMethod(), responseObserver);
    }

    /**
     */
    public void rekey(tkrzw_rpc.TkrzwRpc.RekeyRequest request,
        io.grpc.stub.StreamObserver<tkrzw_rpc.TkrzwRpc.RekeyResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getRekeyMethod(), responseObserver);
    }

    /**
     */
    public void popFirst(tkrzw_rpc.TkrzwRpc.PopFirstRequest request,
        io.grpc.stub.StreamObserver<tkrzw_rpc.TkrzwRpc.PopFirstResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getPopFirstMethod(), responseObserver);
    }

    /**
     */
    public void pushLast(tkrzw_rpc.TkrzwRpc.PushLastRequest request,
        io.grpc.stub.StreamObserver<tkrzw_rpc.TkrzwRpc.PushLastResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getPushLastMethod(), responseObserver);
    }

    /**
     */
    public void count(tkrzw_rpc.TkrzwRpc.CountRequest request,
        io.grpc.stub.StreamObserver<tkrzw_rpc.TkrzwRpc.CountResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getCountMethod(), responseObserver);
    }

    /**
     */
    public void getFileSize(tkrzw_rpc.TkrzwRpc.GetFileSizeRequest request,
        io.grpc.stub.StreamObserver<tkrzw_rpc.TkrzwRpc.GetFileSizeResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getGetFileSizeMethod(), responseObserver);
    }

    /**
     */
    public void clear(tkrzw_rpc.TkrzwRpc.ClearRequest request,
        io.grpc.stub.StreamObserver<tkrzw_rpc.TkrzwRpc.ClearResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getClearMethod(), responseObserver);
    }

    /**
     */
    public void rebuild(tkrzw_rpc.TkrzwRpc.RebuildRequest request,
        io.grpc.stub.StreamObserver<tkrzw_rpc.TkrzwRpc.RebuildResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getRebuildMethod(), responseObserver);
    }

    /**
     */
    public void shouldBeRebuilt(tkrzw_rpc.TkrzwRpc.ShouldBeRebuiltRequest request,
        io.grpc.stub.StreamObserver<tkrzw_rpc.TkrzwRpc.ShouldBeRebuiltResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getShouldBeRebuiltMethod(), responseObserver);
    }

    /**
     */
    public void synchronize(tkrzw_rpc.TkrzwRpc.SynchronizeRequest request,
        io.grpc.stub.StreamObserver<tkrzw_rpc.TkrzwRpc.SynchronizeResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getSynchronizeMethod(), responseObserver);
    }

    /**
     */
    public void search(tkrzw_rpc.TkrzwRpc.SearchRequest request,
        io.grpc.stub.StreamObserver<tkrzw_rpc.TkrzwRpc.SearchResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getSearchMethod(), responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<tkrzw_rpc.TkrzwRpc.StreamRequest> stream(
        io.grpc.stub.StreamObserver<tkrzw_rpc.TkrzwRpc.StreamResponse> responseObserver) {
      return asyncUnimplementedStreamingCall(getStreamMethod(), responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<tkrzw_rpc.TkrzwRpc.IterateRequest> iterate(
        io.grpc.stub.StreamObserver<tkrzw_rpc.TkrzwRpc.IterateResponse> responseObserver) {
      return asyncUnimplementedStreamingCall(getIterateMethod(), responseObserver);
    }

    /**
     */
    public void replicate(tkrzw_rpc.TkrzwRpc.ReplicateRequest request,
        io.grpc.stub.StreamObserver<tkrzw_rpc.TkrzwRpc.ReplicateResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getReplicateMethod(), responseObserver);
    }

    /**
     */
    public void changeMaster(tkrzw_rpc.TkrzwRpc.ChangeMasterRequest request,
        io.grpc.stub.StreamObserver<tkrzw_rpc.TkrzwRpc.ChangeMasterResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getChangeMasterMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getEchoMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                tkrzw_rpc.TkrzwRpc.EchoRequest,
                tkrzw_rpc.TkrzwRpc.EchoResponse>(
                  this, METHODID_ECHO)))
          .addMethod(
            getInspectMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                tkrzw_rpc.TkrzwRpc.InspectRequest,
                tkrzw_rpc.TkrzwRpc.InspectResponse>(
                  this, METHODID_INSPECT)))
          .addMethod(
            getGetMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                tkrzw_rpc.TkrzwRpc.GetRequest,
                tkrzw_rpc.TkrzwRpc.GetResponse>(
                  this, METHODID_GET)))
          .addMethod(
            getGetMultiMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                tkrzw_rpc.TkrzwRpc.GetMultiRequest,
                tkrzw_rpc.TkrzwRpc.GetMultiResponse>(
                  this, METHODID_GET_MULTI)))
          .addMethod(
            getSetMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                tkrzw_rpc.TkrzwRpc.SetRequest,
                tkrzw_rpc.TkrzwRpc.SetResponse>(
                  this, METHODID_SET)))
          .addMethod(
            getSetMultiMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                tkrzw_rpc.TkrzwRpc.SetMultiRequest,
                tkrzw_rpc.TkrzwRpc.SetMultiResponse>(
                  this, METHODID_SET_MULTI)))
          .addMethod(
            getRemoveMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                tkrzw_rpc.TkrzwRpc.RemoveRequest,
                tkrzw_rpc.TkrzwRpc.RemoveResponse>(
                  this, METHODID_REMOVE)))
          .addMethod(
            getRemoveMultiMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                tkrzw_rpc.TkrzwRpc.RemoveMultiRequest,
                tkrzw_rpc.TkrzwRpc.RemoveMultiResponse>(
                  this, METHODID_REMOVE_MULTI)))
          .addMethod(
            getAppendMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                tkrzw_rpc.TkrzwRpc.AppendRequest,
                tkrzw_rpc.TkrzwRpc.AppendResponse>(
                  this, METHODID_APPEND)))
          .addMethod(
            getAppendMultiMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                tkrzw_rpc.TkrzwRpc.AppendMultiRequest,
                tkrzw_rpc.TkrzwRpc.AppendMultiResponse>(
                  this, METHODID_APPEND_MULTI)))
          .addMethod(
            getCompareExchangeMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                tkrzw_rpc.TkrzwRpc.CompareExchangeRequest,
                tkrzw_rpc.TkrzwRpc.CompareExchangeResponse>(
                  this, METHODID_COMPARE_EXCHANGE)))
          .addMethod(
            getIncrementMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                tkrzw_rpc.TkrzwRpc.IncrementRequest,
                tkrzw_rpc.TkrzwRpc.IncrementResponse>(
                  this, METHODID_INCREMENT)))
          .addMethod(
            getCompareExchangeMultiMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                tkrzw_rpc.TkrzwRpc.CompareExchangeMultiRequest,
                tkrzw_rpc.TkrzwRpc.CompareExchangeMultiResponse>(
                  this, METHODID_COMPARE_EXCHANGE_MULTI)))
          .addMethod(
            getRekeyMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                tkrzw_rpc.TkrzwRpc.RekeyRequest,
                tkrzw_rpc.TkrzwRpc.RekeyResponse>(
                  this, METHODID_REKEY)))
          .addMethod(
            getPopFirstMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                tkrzw_rpc.TkrzwRpc.PopFirstRequest,
                tkrzw_rpc.TkrzwRpc.PopFirstResponse>(
                  this, METHODID_POP_FIRST)))
          .addMethod(
            getPushLastMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                tkrzw_rpc.TkrzwRpc.PushLastRequest,
                tkrzw_rpc.TkrzwRpc.PushLastResponse>(
                  this, METHODID_PUSH_LAST)))
          .addMethod(
            getCountMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                tkrzw_rpc.TkrzwRpc.CountRequest,
                tkrzw_rpc.TkrzwRpc.CountResponse>(
                  this, METHODID_COUNT)))
          .addMethod(
            getGetFileSizeMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                tkrzw_rpc.TkrzwRpc.GetFileSizeRequest,
                tkrzw_rpc.TkrzwRpc.GetFileSizeResponse>(
                  this, METHODID_GET_FILE_SIZE)))
          .addMethod(
            getClearMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                tkrzw_rpc.TkrzwRpc.ClearRequest,
                tkrzw_rpc.TkrzwRpc.ClearResponse>(
                  this, METHODID_CLEAR)))
          .addMethod(
            getRebuildMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                tkrzw_rpc.TkrzwRpc.RebuildRequest,
                tkrzw_rpc.TkrzwRpc.RebuildResponse>(
                  this, METHODID_REBUILD)))
          .addMethod(
            getShouldBeRebuiltMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                tkrzw_rpc.TkrzwRpc.ShouldBeRebuiltRequest,
                tkrzw_rpc.TkrzwRpc.ShouldBeRebuiltResponse>(
                  this, METHODID_SHOULD_BE_REBUILT)))
          .addMethod(
            getSynchronizeMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                tkrzw_rpc.TkrzwRpc.SynchronizeRequest,
                tkrzw_rpc.TkrzwRpc.SynchronizeResponse>(
                  this, METHODID_SYNCHRONIZE)))
          .addMethod(
            getSearchMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                tkrzw_rpc.TkrzwRpc.SearchRequest,
                tkrzw_rpc.TkrzwRpc.SearchResponse>(
                  this, METHODID_SEARCH)))
          .addMethod(
            getStreamMethod(),
            asyncBidiStreamingCall(
              new MethodHandlers<
                tkrzw_rpc.TkrzwRpc.StreamRequest,
                tkrzw_rpc.TkrzwRpc.StreamResponse>(
                  this, METHODID_STREAM)))
          .addMethod(
            getIterateMethod(),
            asyncBidiStreamingCall(
              new MethodHandlers<
                tkrzw_rpc.TkrzwRpc.IterateRequest,
                tkrzw_rpc.TkrzwRpc.IterateResponse>(
                  this, METHODID_ITERATE)))
          .addMethod(
            getReplicateMethod(),
            asyncServerStreamingCall(
              new MethodHandlers<
                tkrzw_rpc.TkrzwRpc.ReplicateRequest,
                tkrzw_rpc.TkrzwRpc.ReplicateResponse>(
                  this, METHODID_REPLICATE)))
          .addMethod(
            getChangeMasterMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                tkrzw_rpc.TkrzwRpc.ChangeMasterRequest,
                tkrzw_rpc.TkrzwRpc.ChangeMasterResponse>(
                  this, METHODID_CHANGE_MASTER)))
          .build();
    }
  }

  /**
   * <pre>
   * Definition of the database service.
   * </pre>
   */
  public static final class DBMServiceStub extends io.grpc.stub.AbstractAsyncStub<DBMServiceStub> {
    private DBMServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected DBMServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new DBMServiceStub(channel, callOptions);
    }

    /**
     */
    public void echo(tkrzw_rpc.TkrzwRpc.EchoRequest request,
        io.grpc.stub.StreamObserver<tkrzw_rpc.TkrzwRpc.EchoResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getEchoMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void inspect(tkrzw_rpc.TkrzwRpc.InspectRequest request,
        io.grpc.stub.StreamObserver<tkrzw_rpc.TkrzwRpc.InspectResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getInspectMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void get(tkrzw_rpc.TkrzwRpc.GetRequest request,
        io.grpc.stub.StreamObserver<tkrzw_rpc.TkrzwRpc.GetResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getMulti(tkrzw_rpc.TkrzwRpc.GetMultiRequest request,
        io.grpc.stub.StreamObserver<tkrzw_rpc.TkrzwRpc.GetMultiResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetMultiMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void set(tkrzw_rpc.TkrzwRpc.SetRequest request,
        io.grpc.stub.StreamObserver<tkrzw_rpc.TkrzwRpc.SetResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSetMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void setMulti(tkrzw_rpc.TkrzwRpc.SetMultiRequest request,
        io.grpc.stub.StreamObserver<tkrzw_rpc.TkrzwRpc.SetMultiResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSetMultiMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void remove(tkrzw_rpc.TkrzwRpc.RemoveRequest request,
        io.grpc.stub.StreamObserver<tkrzw_rpc.TkrzwRpc.RemoveResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getRemoveMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void removeMulti(tkrzw_rpc.TkrzwRpc.RemoveMultiRequest request,
        io.grpc.stub.StreamObserver<tkrzw_rpc.TkrzwRpc.RemoveMultiResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getRemoveMultiMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void append(tkrzw_rpc.TkrzwRpc.AppendRequest request,
        io.grpc.stub.StreamObserver<tkrzw_rpc.TkrzwRpc.AppendResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getAppendMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void appendMulti(tkrzw_rpc.TkrzwRpc.AppendMultiRequest request,
        io.grpc.stub.StreamObserver<tkrzw_rpc.TkrzwRpc.AppendMultiResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getAppendMultiMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void compareExchange(tkrzw_rpc.TkrzwRpc.CompareExchangeRequest request,
        io.grpc.stub.StreamObserver<tkrzw_rpc.TkrzwRpc.CompareExchangeResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCompareExchangeMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void increment(tkrzw_rpc.TkrzwRpc.IncrementRequest request,
        io.grpc.stub.StreamObserver<tkrzw_rpc.TkrzwRpc.IncrementResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getIncrementMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void compareExchangeMulti(tkrzw_rpc.TkrzwRpc.CompareExchangeMultiRequest request,
        io.grpc.stub.StreamObserver<tkrzw_rpc.TkrzwRpc.CompareExchangeMultiResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCompareExchangeMultiMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void rekey(tkrzw_rpc.TkrzwRpc.RekeyRequest request,
        io.grpc.stub.StreamObserver<tkrzw_rpc.TkrzwRpc.RekeyResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getRekeyMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void popFirst(tkrzw_rpc.TkrzwRpc.PopFirstRequest request,
        io.grpc.stub.StreamObserver<tkrzw_rpc.TkrzwRpc.PopFirstResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getPopFirstMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void pushLast(tkrzw_rpc.TkrzwRpc.PushLastRequest request,
        io.grpc.stub.StreamObserver<tkrzw_rpc.TkrzwRpc.PushLastResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getPushLastMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void count(tkrzw_rpc.TkrzwRpc.CountRequest request,
        io.grpc.stub.StreamObserver<tkrzw_rpc.TkrzwRpc.CountResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCountMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getFileSize(tkrzw_rpc.TkrzwRpc.GetFileSizeRequest request,
        io.grpc.stub.StreamObserver<tkrzw_rpc.TkrzwRpc.GetFileSizeResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetFileSizeMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void clear(tkrzw_rpc.TkrzwRpc.ClearRequest request,
        io.grpc.stub.StreamObserver<tkrzw_rpc.TkrzwRpc.ClearResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getClearMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void rebuild(tkrzw_rpc.TkrzwRpc.RebuildRequest request,
        io.grpc.stub.StreamObserver<tkrzw_rpc.TkrzwRpc.RebuildResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getRebuildMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void shouldBeRebuilt(tkrzw_rpc.TkrzwRpc.ShouldBeRebuiltRequest request,
        io.grpc.stub.StreamObserver<tkrzw_rpc.TkrzwRpc.ShouldBeRebuiltResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getShouldBeRebuiltMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void synchronize(tkrzw_rpc.TkrzwRpc.SynchronizeRequest request,
        io.grpc.stub.StreamObserver<tkrzw_rpc.TkrzwRpc.SynchronizeResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSynchronizeMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void search(tkrzw_rpc.TkrzwRpc.SearchRequest request,
        io.grpc.stub.StreamObserver<tkrzw_rpc.TkrzwRpc.SearchResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSearchMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<tkrzw_rpc.TkrzwRpc.StreamRequest> stream(
        io.grpc.stub.StreamObserver<tkrzw_rpc.TkrzwRpc.StreamResponse> responseObserver) {
      return asyncBidiStreamingCall(
          getChannel().newCall(getStreamMethod(), getCallOptions()), responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<tkrzw_rpc.TkrzwRpc.IterateRequest> iterate(
        io.grpc.stub.StreamObserver<tkrzw_rpc.TkrzwRpc.IterateResponse> responseObserver) {
      return asyncBidiStreamingCall(
          getChannel().newCall(getIterateMethod(), getCallOptions()), responseObserver);
    }

    /**
     */
    public void replicate(tkrzw_rpc.TkrzwRpc.ReplicateRequest request,
        io.grpc.stub.StreamObserver<tkrzw_rpc.TkrzwRpc.ReplicateResponse> responseObserver) {
      asyncServerStreamingCall(
          getChannel().newCall(getReplicateMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void changeMaster(tkrzw_rpc.TkrzwRpc.ChangeMasterRequest request,
        io.grpc.stub.StreamObserver<tkrzw_rpc.TkrzwRpc.ChangeMasterResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getChangeMasterMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   * Definition of the database service.
   * </pre>
   */
  public static final class DBMServiceBlockingStub extends io.grpc.stub.AbstractBlockingStub<DBMServiceBlockingStub> {
    private DBMServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected DBMServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new DBMServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public tkrzw_rpc.TkrzwRpc.EchoResponse echo(tkrzw_rpc.TkrzwRpc.EchoRequest request) {
      return blockingUnaryCall(
          getChannel(), getEchoMethod(), getCallOptions(), request);
    }

    /**
     */
    public tkrzw_rpc.TkrzwRpc.InspectResponse inspect(tkrzw_rpc.TkrzwRpc.InspectRequest request) {
      return blockingUnaryCall(
          getChannel(), getInspectMethod(), getCallOptions(), request);
    }

    /**
     */
    public tkrzw_rpc.TkrzwRpc.GetResponse get(tkrzw_rpc.TkrzwRpc.GetRequest request) {
      return blockingUnaryCall(
          getChannel(), getGetMethod(), getCallOptions(), request);
    }

    /**
     */
    public tkrzw_rpc.TkrzwRpc.GetMultiResponse getMulti(tkrzw_rpc.TkrzwRpc.GetMultiRequest request) {
      return blockingUnaryCall(
          getChannel(), getGetMultiMethod(), getCallOptions(), request);
    }

    /**
     */
    public tkrzw_rpc.TkrzwRpc.SetResponse set(tkrzw_rpc.TkrzwRpc.SetRequest request) {
      return blockingUnaryCall(
          getChannel(), getSetMethod(), getCallOptions(), request);
    }

    /**
     */
    public tkrzw_rpc.TkrzwRpc.SetMultiResponse setMulti(tkrzw_rpc.TkrzwRpc.SetMultiRequest request) {
      return blockingUnaryCall(
          getChannel(), getSetMultiMethod(), getCallOptions(), request);
    }

    /**
     */
    public tkrzw_rpc.TkrzwRpc.RemoveResponse remove(tkrzw_rpc.TkrzwRpc.RemoveRequest request) {
      return blockingUnaryCall(
          getChannel(), getRemoveMethod(), getCallOptions(), request);
    }

    /**
     */
    public tkrzw_rpc.TkrzwRpc.RemoveMultiResponse removeMulti(tkrzw_rpc.TkrzwRpc.RemoveMultiRequest request) {
      return blockingUnaryCall(
          getChannel(), getRemoveMultiMethod(), getCallOptions(), request);
    }

    /**
     */
    public tkrzw_rpc.TkrzwRpc.AppendResponse append(tkrzw_rpc.TkrzwRpc.AppendRequest request) {
      return blockingUnaryCall(
          getChannel(), getAppendMethod(), getCallOptions(), request);
    }

    /**
     */
    public tkrzw_rpc.TkrzwRpc.AppendMultiResponse appendMulti(tkrzw_rpc.TkrzwRpc.AppendMultiRequest request) {
      return blockingUnaryCall(
          getChannel(), getAppendMultiMethod(), getCallOptions(), request);
    }

    /**
     */
    public tkrzw_rpc.TkrzwRpc.CompareExchangeResponse compareExchange(tkrzw_rpc.TkrzwRpc.CompareExchangeRequest request) {
      return blockingUnaryCall(
          getChannel(), getCompareExchangeMethod(), getCallOptions(), request);
    }

    /**
     */
    public tkrzw_rpc.TkrzwRpc.IncrementResponse increment(tkrzw_rpc.TkrzwRpc.IncrementRequest request) {
      return blockingUnaryCall(
          getChannel(), getIncrementMethod(), getCallOptions(), request);
    }

    /**
     */
    public tkrzw_rpc.TkrzwRpc.CompareExchangeMultiResponse compareExchangeMulti(tkrzw_rpc.TkrzwRpc.CompareExchangeMultiRequest request) {
      return blockingUnaryCall(
          getChannel(), getCompareExchangeMultiMethod(), getCallOptions(), request);
    }

    /**
     */
    public tkrzw_rpc.TkrzwRpc.RekeyResponse rekey(tkrzw_rpc.TkrzwRpc.RekeyRequest request) {
      return blockingUnaryCall(
          getChannel(), getRekeyMethod(), getCallOptions(), request);
    }

    /**
     */
    public tkrzw_rpc.TkrzwRpc.PopFirstResponse popFirst(tkrzw_rpc.TkrzwRpc.PopFirstRequest request) {
      return blockingUnaryCall(
          getChannel(), getPopFirstMethod(), getCallOptions(), request);
    }

    /**
     */
    public tkrzw_rpc.TkrzwRpc.PushLastResponse pushLast(tkrzw_rpc.TkrzwRpc.PushLastRequest request) {
      return blockingUnaryCall(
          getChannel(), getPushLastMethod(), getCallOptions(), request);
    }

    /**
     */
    public tkrzw_rpc.TkrzwRpc.CountResponse count(tkrzw_rpc.TkrzwRpc.CountRequest request) {
      return blockingUnaryCall(
          getChannel(), getCountMethod(), getCallOptions(), request);
    }

    /**
     */
    public tkrzw_rpc.TkrzwRpc.GetFileSizeResponse getFileSize(tkrzw_rpc.TkrzwRpc.GetFileSizeRequest request) {
      return blockingUnaryCall(
          getChannel(), getGetFileSizeMethod(), getCallOptions(), request);
    }

    /**
     */
    public tkrzw_rpc.TkrzwRpc.ClearResponse clear(tkrzw_rpc.TkrzwRpc.ClearRequest request) {
      return blockingUnaryCall(
          getChannel(), getClearMethod(), getCallOptions(), request);
    }

    /**
     */
    public tkrzw_rpc.TkrzwRpc.RebuildResponse rebuild(tkrzw_rpc.TkrzwRpc.RebuildRequest request) {
      return blockingUnaryCall(
          getChannel(), getRebuildMethod(), getCallOptions(), request);
    }

    /**
     */
    public tkrzw_rpc.TkrzwRpc.ShouldBeRebuiltResponse shouldBeRebuilt(tkrzw_rpc.TkrzwRpc.ShouldBeRebuiltRequest request) {
      return blockingUnaryCall(
          getChannel(), getShouldBeRebuiltMethod(), getCallOptions(), request);
    }

    /**
     */
    public tkrzw_rpc.TkrzwRpc.SynchronizeResponse synchronize(tkrzw_rpc.TkrzwRpc.SynchronizeRequest request) {
      return blockingUnaryCall(
          getChannel(), getSynchronizeMethod(), getCallOptions(), request);
    }

    /**
     */
    public tkrzw_rpc.TkrzwRpc.SearchResponse search(tkrzw_rpc.TkrzwRpc.SearchRequest request) {
      return blockingUnaryCall(
          getChannel(), getSearchMethod(), getCallOptions(), request);
    }

    /**
     */
    public java.util.Iterator<tkrzw_rpc.TkrzwRpc.ReplicateResponse> replicate(
        tkrzw_rpc.TkrzwRpc.ReplicateRequest request) {
      return blockingServerStreamingCall(
          getChannel(), getReplicateMethod(), getCallOptions(), request);
    }

    /**
     */
    public tkrzw_rpc.TkrzwRpc.ChangeMasterResponse changeMaster(tkrzw_rpc.TkrzwRpc.ChangeMasterRequest request) {
      return blockingUnaryCall(
          getChannel(), getChangeMasterMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * Definition of the database service.
   * </pre>
   */
  public static final class DBMServiceFutureStub extends io.grpc.stub.AbstractFutureStub<DBMServiceFutureStub> {
    private DBMServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected DBMServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new DBMServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<tkrzw_rpc.TkrzwRpc.EchoResponse> echo(
        tkrzw_rpc.TkrzwRpc.EchoRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getEchoMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<tkrzw_rpc.TkrzwRpc.InspectResponse> inspect(
        tkrzw_rpc.TkrzwRpc.InspectRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getInspectMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<tkrzw_rpc.TkrzwRpc.GetResponse> get(
        tkrzw_rpc.TkrzwRpc.GetRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGetMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<tkrzw_rpc.TkrzwRpc.GetMultiResponse> getMulti(
        tkrzw_rpc.TkrzwRpc.GetMultiRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGetMultiMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<tkrzw_rpc.TkrzwRpc.SetResponse> set(
        tkrzw_rpc.TkrzwRpc.SetRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getSetMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<tkrzw_rpc.TkrzwRpc.SetMultiResponse> setMulti(
        tkrzw_rpc.TkrzwRpc.SetMultiRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getSetMultiMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<tkrzw_rpc.TkrzwRpc.RemoveResponse> remove(
        tkrzw_rpc.TkrzwRpc.RemoveRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getRemoveMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<tkrzw_rpc.TkrzwRpc.RemoveMultiResponse> removeMulti(
        tkrzw_rpc.TkrzwRpc.RemoveMultiRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getRemoveMultiMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<tkrzw_rpc.TkrzwRpc.AppendResponse> append(
        tkrzw_rpc.TkrzwRpc.AppendRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getAppendMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<tkrzw_rpc.TkrzwRpc.AppendMultiResponse> appendMulti(
        tkrzw_rpc.TkrzwRpc.AppendMultiRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getAppendMultiMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<tkrzw_rpc.TkrzwRpc.CompareExchangeResponse> compareExchange(
        tkrzw_rpc.TkrzwRpc.CompareExchangeRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getCompareExchangeMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<tkrzw_rpc.TkrzwRpc.IncrementResponse> increment(
        tkrzw_rpc.TkrzwRpc.IncrementRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getIncrementMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<tkrzw_rpc.TkrzwRpc.CompareExchangeMultiResponse> compareExchangeMulti(
        tkrzw_rpc.TkrzwRpc.CompareExchangeMultiRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getCompareExchangeMultiMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<tkrzw_rpc.TkrzwRpc.RekeyResponse> rekey(
        tkrzw_rpc.TkrzwRpc.RekeyRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getRekeyMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<tkrzw_rpc.TkrzwRpc.PopFirstResponse> popFirst(
        tkrzw_rpc.TkrzwRpc.PopFirstRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getPopFirstMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<tkrzw_rpc.TkrzwRpc.PushLastResponse> pushLast(
        tkrzw_rpc.TkrzwRpc.PushLastRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getPushLastMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<tkrzw_rpc.TkrzwRpc.CountResponse> count(
        tkrzw_rpc.TkrzwRpc.CountRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getCountMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<tkrzw_rpc.TkrzwRpc.GetFileSizeResponse> getFileSize(
        tkrzw_rpc.TkrzwRpc.GetFileSizeRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGetFileSizeMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<tkrzw_rpc.TkrzwRpc.ClearResponse> clear(
        tkrzw_rpc.TkrzwRpc.ClearRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getClearMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<tkrzw_rpc.TkrzwRpc.RebuildResponse> rebuild(
        tkrzw_rpc.TkrzwRpc.RebuildRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getRebuildMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<tkrzw_rpc.TkrzwRpc.ShouldBeRebuiltResponse> shouldBeRebuilt(
        tkrzw_rpc.TkrzwRpc.ShouldBeRebuiltRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getShouldBeRebuiltMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<tkrzw_rpc.TkrzwRpc.SynchronizeResponse> synchronize(
        tkrzw_rpc.TkrzwRpc.SynchronizeRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getSynchronizeMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<tkrzw_rpc.TkrzwRpc.SearchResponse> search(
        tkrzw_rpc.TkrzwRpc.SearchRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getSearchMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<tkrzw_rpc.TkrzwRpc.ChangeMasterResponse> changeMaster(
        tkrzw_rpc.TkrzwRpc.ChangeMasterRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getChangeMasterMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_ECHO = 0;
  private static final int METHODID_INSPECT = 1;
  private static final int METHODID_GET = 2;
  private static final int METHODID_GET_MULTI = 3;
  private static final int METHODID_SET = 4;
  private static final int METHODID_SET_MULTI = 5;
  private static final int METHODID_REMOVE = 6;
  private static final int METHODID_REMOVE_MULTI = 7;
  private static final int METHODID_APPEND = 8;
  private static final int METHODID_APPEND_MULTI = 9;
  private static final int METHODID_COMPARE_EXCHANGE = 10;
  private static final int METHODID_INCREMENT = 11;
  private static final int METHODID_COMPARE_EXCHANGE_MULTI = 12;
  private static final int METHODID_REKEY = 13;
  private static final int METHODID_POP_FIRST = 14;
  private static final int METHODID_PUSH_LAST = 15;
  private static final int METHODID_COUNT = 16;
  private static final int METHODID_GET_FILE_SIZE = 17;
  private static final int METHODID_CLEAR = 18;
  private static final int METHODID_REBUILD = 19;
  private static final int METHODID_SHOULD_BE_REBUILT = 20;
  private static final int METHODID_SYNCHRONIZE = 21;
  private static final int METHODID_SEARCH = 22;
  private static final int METHODID_REPLICATE = 23;
  private static final int METHODID_CHANGE_MASTER = 24;
  private static final int METHODID_STREAM = 25;
  private static final int METHODID_ITERATE = 26;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final DBMServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(DBMServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_ECHO:
          serviceImpl.echo((tkrzw_rpc.TkrzwRpc.EchoRequest) request,
              (io.grpc.stub.StreamObserver<tkrzw_rpc.TkrzwRpc.EchoResponse>) responseObserver);
          break;
        case METHODID_INSPECT:
          serviceImpl.inspect((tkrzw_rpc.TkrzwRpc.InspectRequest) request,
              (io.grpc.stub.StreamObserver<tkrzw_rpc.TkrzwRpc.InspectResponse>) responseObserver);
          break;
        case METHODID_GET:
          serviceImpl.get((tkrzw_rpc.TkrzwRpc.GetRequest) request,
              (io.grpc.stub.StreamObserver<tkrzw_rpc.TkrzwRpc.GetResponse>) responseObserver);
          break;
        case METHODID_GET_MULTI:
          serviceImpl.getMulti((tkrzw_rpc.TkrzwRpc.GetMultiRequest) request,
              (io.grpc.stub.StreamObserver<tkrzw_rpc.TkrzwRpc.GetMultiResponse>) responseObserver);
          break;
        case METHODID_SET:
          serviceImpl.set((tkrzw_rpc.TkrzwRpc.SetRequest) request,
              (io.grpc.stub.StreamObserver<tkrzw_rpc.TkrzwRpc.SetResponse>) responseObserver);
          break;
        case METHODID_SET_MULTI:
          serviceImpl.setMulti((tkrzw_rpc.TkrzwRpc.SetMultiRequest) request,
              (io.grpc.stub.StreamObserver<tkrzw_rpc.TkrzwRpc.SetMultiResponse>) responseObserver);
          break;
        case METHODID_REMOVE:
          serviceImpl.remove((tkrzw_rpc.TkrzwRpc.RemoveRequest) request,
              (io.grpc.stub.StreamObserver<tkrzw_rpc.TkrzwRpc.RemoveResponse>) responseObserver);
          break;
        case METHODID_REMOVE_MULTI:
          serviceImpl.removeMulti((tkrzw_rpc.TkrzwRpc.RemoveMultiRequest) request,
              (io.grpc.stub.StreamObserver<tkrzw_rpc.TkrzwRpc.RemoveMultiResponse>) responseObserver);
          break;
        case METHODID_APPEND:
          serviceImpl.append((tkrzw_rpc.TkrzwRpc.AppendRequest) request,
              (io.grpc.stub.StreamObserver<tkrzw_rpc.TkrzwRpc.AppendResponse>) responseObserver);
          break;
        case METHODID_APPEND_MULTI:
          serviceImpl.appendMulti((tkrzw_rpc.TkrzwRpc.AppendMultiRequest) request,
              (io.grpc.stub.StreamObserver<tkrzw_rpc.TkrzwRpc.AppendMultiResponse>) responseObserver);
          break;
        case METHODID_COMPARE_EXCHANGE:
          serviceImpl.compareExchange((tkrzw_rpc.TkrzwRpc.CompareExchangeRequest) request,
              (io.grpc.stub.StreamObserver<tkrzw_rpc.TkrzwRpc.CompareExchangeResponse>) responseObserver);
          break;
        case METHODID_INCREMENT:
          serviceImpl.increment((tkrzw_rpc.TkrzwRpc.IncrementRequest) request,
              (io.grpc.stub.StreamObserver<tkrzw_rpc.TkrzwRpc.IncrementResponse>) responseObserver);
          break;
        case METHODID_COMPARE_EXCHANGE_MULTI:
          serviceImpl.compareExchangeMulti((tkrzw_rpc.TkrzwRpc.CompareExchangeMultiRequest) request,
              (io.grpc.stub.StreamObserver<tkrzw_rpc.TkrzwRpc.CompareExchangeMultiResponse>) responseObserver);
          break;
        case METHODID_REKEY:
          serviceImpl.rekey((tkrzw_rpc.TkrzwRpc.RekeyRequest) request,
              (io.grpc.stub.StreamObserver<tkrzw_rpc.TkrzwRpc.RekeyResponse>) responseObserver);
          break;
        case METHODID_POP_FIRST:
          serviceImpl.popFirst((tkrzw_rpc.TkrzwRpc.PopFirstRequest) request,
              (io.grpc.stub.StreamObserver<tkrzw_rpc.TkrzwRpc.PopFirstResponse>) responseObserver);
          break;
        case METHODID_PUSH_LAST:
          serviceImpl.pushLast((tkrzw_rpc.TkrzwRpc.PushLastRequest) request,
              (io.grpc.stub.StreamObserver<tkrzw_rpc.TkrzwRpc.PushLastResponse>) responseObserver);
          break;
        case METHODID_COUNT:
          serviceImpl.count((tkrzw_rpc.TkrzwRpc.CountRequest) request,
              (io.grpc.stub.StreamObserver<tkrzw_rpc.TkrzwRpc.CountResponse>) responseObserver);
          break;
        case METHODID_GET_FILE_SIZE:
          serviceImpl.getFileSize((tkrzw_rpc.TkrzwRpc.GetFileSizeRequest) request,
              (io.grpc.stub.StreamObserver<tkrzw_rpc.TkrzwRpc.GetFileSizeResponse>) responseObserver);
          break;
        case METHODID_CLEAR:
          serviceImpl.clear((tkrzw_rpc.TkrzwRpc.ClearRequest) request,
              (io.grpc.stub.StreamObserver<tkrzw_rpc.TkrzwRpc.ClearResponse>) responseObserver);
          break;
        case METHODID_REBUILD:
          serviceImpl.rebuild((tkrzw_rpc.TkrzwRpc.RebuildRequest) request,
              (io.grpc.stub.StreamObserver<tkrzw_rpc.TkrzwRpc.RebuildResponse>) responseObserver);
          break;
        case METHODID_SHOULD_BE_REBUILT:
          serviceImpl.shouldBeRebuilt((tkrzw_rpc.TkrzwRpc.ShouldBeRebuiltRequest) request,
              (io.grpc.stub.StreamObserver<tkrzw_rpc.TkrzwRpc.ShouldBeRebuiltResponse>) responseObserver);
          break;
        case METHODID_SYNCHRONIZE:
          serviceImpl.synchronize((tkrzw_rpc.TkrzwRpc.SynchronizeRequest) request,
              (io.grpc.stub.StreamObserver<tkrzw_rpc.TkrzwRpc.SynchronizeResponse>) responseObserver);
          break;
        case METHODID_SEARCH:
          serviceImpl.search((tkrzw_rpc.TkrzwRpc.SearchRequest) request,
              (io.grpc.stub.StreamObserver<tkrzw_rpc.TkrzwRpc.SearchResponse>) responseObserver);
          break;
        case METHODID_REPLICATE:
          serviceImpl.replicate((tkrzw_rpc.TkrzwRpc.ReplicateRequest) request,
              (io.grpc.stub.StreamObserver<tkrzw_rpc.TkrzwRpc.ReplicateResponse>) responseObserver);
          break;
        case METHODID_CHANGE_MASTER:
          serviceImpl.changeMaster((tkrzw_rpc.TkrzwRpc.ChangeMasterRequest) request,
              (io.grpc.stub.StreamObserver<tkrzw_rpc.TkrzwRpc.ChangeMasterResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_STREAM:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.stream(
              (io.grpc.stub.StreamObserver<tkrzw_rpc.TkrzwRpc.StreamResponse>) responseObserver);
        case METHODID_ITERATE:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.iterate(
              (io.grpc.stub.StreamObserver<tkrzw_rpc.TkrzwRpc.IterateResponse>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class DBMServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    DBMServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return tkrzw_rpc.TkrzwRpc.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("DBMService");
    }
  }

  private static final class DBMServiceFileDescriptorSupplier
      extends DBMServiceBaseDescriptorSupplier {
    DBMServiceFileDescriptorSupplier() {}
  }

  private static final class DBMServiceMethodDescriptorSupplier
      extends DBMServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    DBMServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (DBMServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new DBMServiceFileDescriptorSupplier())
              .addMethod(getEchoMethod())
              .addMethod(getInspectMethod())
              .addMethod(getGetMethod())
              .addMethod(getGetMultiMethod())
              .addMethod(getSetMethod())
              .addMethod(getSetMultiMethod())
              .addMethod(getRemoveMethod())
              .addMethod(getRemoveMultiMethod())
              .addMethod(getAppendMethod())
              .addMethod(getAppendMultiMethod())
              .addMethod(getCompareExchangeMethod())
              .addMethod(getIncrementMethod())
              .addMethod(getCompareExchangeMultiMethod())
              .addMethod(getRekeyMethod())
              .addMethod(getPopFirstMethod())
              .addMethod(getPushLastMethod())
              .addMethod(getCountMethod())
              .addMethod(getGetFileSizeMethod())
              .addMethod(getClearMethod())
              .addMethod(getRebuildMethod())
              .addMethod(getShouldBeRebuiltMethod())
              .addMethod(getSynchronizeMethod())
              .addMethod(getSearchMethod())
              .addMethod(getStreamMethod())
              .addMethod(getIterateMethod())
              .addMethod(getReplicateMethod())
              .addMethod(getChangeMasterMethod())
              .build();
        }
      }
    }
    return result;
  }
}
