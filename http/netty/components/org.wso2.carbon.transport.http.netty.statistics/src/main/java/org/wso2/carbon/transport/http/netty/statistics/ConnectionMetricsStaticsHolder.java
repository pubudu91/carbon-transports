/*
 * Copyright (c) 2016, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * WSO2 Inc. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.wso2.carbon.transport.http.netty.statistics;

import org.wso2.carbon.metrics.manager.Timer;

/**
 * Holds the Connection related Latency Metrics
 */
public class ConnectionMetricsStaticsHolder implements MetricsStaticsHolder {

    private Timer connectionTimer = null;
    private Timer.Context connectionTimerContext = null;

    public ConnectionMetricsStaticsHolder(String type, TimerHolder timerHolder) {

        if (type.equals(MetricsConstants.TYPE_CLIENT_CONNECTION)) {
            connectionTimer = timerHolder.getClientConnectionTimer();
        } else {
            connectionTimer = timerHolder.getServerConnectionTimer();
        }
    }

    @Override
    public boolean startTimer(String timer) {
        this.connectionTimerContext = this.connectionTimer.start();
        return true;
    }

    @Override
    public boolean stopTimer(String timer) {
        if (this.connectionTimerContext != null) {
            this.connectionTimerContext.stop();
            return true;
        } else {
            return false;
        }
    }

}
