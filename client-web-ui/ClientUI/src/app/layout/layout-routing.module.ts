import { NgModule } from "@angular/core";
import { Routes, RouterModule } from "@angular/router";
import { LayoutComponent } from "./layout.component";

const routes: Routes = [
  {
    path: "",
    component: LayoutComponent,
    children: [
      { path: "", redirectTo: "dashboard", pathMatch: "prefix" },
      {
        path: "home",
        loadChildren: () =>
          import("./dashboard/dashboard.module").then(m => m.DashboardModule)
      },
      {
        path: "my-profile",
        loadChildren: () =>
          import("./my-profile/my-profile.module").then(m => m.MyProfileModule)
      },
      {
        path: "my-partner-profile",
        loadChildren: () =>
          import("./my-partner-profile/my-partner.module").then(
            m => m.MyPartnerModule
          )
      },

      {
        path: "personality-type-test",
        loadChildren: () =>
          import("./personality-type-test/personality-type-test.module").then(
            m => m.PersonalityTypeTestModule
          )
      },

      {
        path: "psych-tools",
        loadChildren: () =>
          import("./psych-tools/psych-tools.module").then(
            m => m.PsychToolsModule
          )
      },

      {
        path: "relationship-profile",
        loadChildren: () =>
          import("./relationship-profile/relationship-profile.module").then(
            m => m.RelationshipProfileModule
          )
      },

      {
        path: "charts",
        loadChildren: () =>
          import("./charts/charts.module").then(m => m.ChartsModule)
      },
      {
        path: "tables",
        loadChildren: () =>
          import("./tables/tables.module").then(m => m.TablesModule)
      },
      {
        path: "forms",
        loadChildren: () => import("./form/form.module").then(m => m.FormModule)
      },
      {
        path: "bs-element",
        loadChildren: () =>
          import("./bs-element/bs-element.module").then(m => m.BsElementModule)
      },
      {
        path: "grid",
        loadChildren: () => import("./grid/grid.module").then(m => m.GridModule)
      },
      {
        path: "components",
        loadChildren: () =>
          import("./bs-component/bs-component.module").then(
            m => m.BsComponentModule
          )
      },
      {
        path: "blank-page",
        loadChildren: () =>
          import("./blank-page/blank-page.module").then(m => m.BlankPageModule)
      },
      {
        path: "my-my-profile",
        loadChildren: () =>
          import("./my-profile/my-profile.module").then(m => m.MyProfileModule)
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class LayoutRoutingModule {}
