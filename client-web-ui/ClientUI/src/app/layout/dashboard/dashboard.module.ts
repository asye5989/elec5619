import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NgbCarouselModule, NgbAlertModule } from '@ng-bootstrap/ng-bootstrap';

import { DashboardRoutingModule } from './dashboard-routing.module';
import { DashboardComponent } from './dashboard.component';
import {
    TimelineComponent,
    NotificationComponent,
    ChatComponent
} from './components';
import { StatModule } from '../../shared';
import { FeatureArticleComponent } from './components/feature-article/feature-article.component';

@NgModule({
    imports: [
        CommonModule,
        NgbCarouselModule,
        NgbAlertModule,
        DashboardRoutingModule,
        StatModule
    ],
    declarations: [
        DashboardComponent,
        TimelineComponent,
        NotificationComponent,
        ChatComponent,
        FeatureArticleComponent
    ]
})
export class DashboardModule {}
