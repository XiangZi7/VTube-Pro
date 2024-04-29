<template>
      <div class="grid gap-6">
        <div class="rounded-lg border bg-card text-card-foreground shadow-sm" data-v0-t="card">
          <div class="flex flex-col space-y-1.5 p-6">
            <h3 class="whitespace-nowrap text-2xl font-semibold leading-none tracking-tight">Profile</h3>
            <p class="text-sm text-muted-foreground">Update your profile information.</p>
          </div>
          <div class="p-6 space-y-4">
            <div class="flex items-center gap-4">
              <span class="relative flex shrink-0 overflow-hidden rounded-full h-16 w-16">
                <span class="flex h-full w-full items-center justify-center rounded-full bg-muted">JP</span>
              </span>
              <div>
                <button class="inline-flex items-center justify-center whitespace-nowrap rounded-md text-sm font-medium ring-offset-background transition-colors focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-ring focus-visible:ring-offset-2 disabled:pointer-events-none disabled:opacity-50 border border-input bg-background hover:bg-accent hover:text-accent-foreground h-10 px-4 py-2">
                  Change photo
                </button>
                <p class="text-sm text-gray-500 dark:text-gray-400">JPG, GIF or PNG. Max size 1MB.</p>
              </div>
            </div>
            <div class="space-y-2">
              <label
                  class="text-sm font-medium leading-none peer-disabled:cursor-not-allowed peer-disabled:opacity-70"
                  for="name"
              >
                Name
              </label>
              <input
                  class="flex h-10 w-full rounded-md border border-input bg-background px-3 py-2 text-sm ring-offset-background file:border-0 file:bg-transparent file:text-sm file:font-medium placeholder:text-muted-foreground focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-ring focus-visible:ring-offset-2 disabled:cursor-not-allowed disabled:opacity-50"
                  id="name"
                  placeholder="Enter your name"
                  value="Jared Palmer"
              />
            </div>
            <div class="space-y-2">
              <label
                  class="text-sm font-medium leading-none peer-disabled:cursor-not-allowed peer-disabled:opacity-70"
                  for="email"
              >
                Email
              </label>
              <input
                  class="flex h-10 w-full rounded-md border border-input bg-background px-3 py-2 text-sm ring-offset-background file:border-0 file:bg-transparent file:text-sm file:font-medium placeholder:text-muted-foreground focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-ring focus-visible:ring-offset-2 disabled:cursor-not-allowed disabled:opacity-50"
                  id="email"
                  placeholder="Enter your email"
                  type="email"
                  value="m@example.com"
              />
            </div>
          </div>
          <div class="flex items-center p-6">
            <button class="inline-flex items-center justify-center whitespace-nowrap rounded-md text-sm font-medium ring-offset-background transition-colors focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-ring focus-visible:ring-offset-2 disabled:pointer-events-none disabled:opacity-50 bg-primary text-primary-foreground hover:bg-primary/90 h-10 px-4 py-2 ml-auto">
              Save
            </button>
          </div>
        </div>
        <div class="rounded-lg border bg-card text-card-foreground shadow-sm" data-v0-t="card">
          <div class="flex flex-col space-y-1.5 p-6">
            <h3 class="whitespace-nowrap text-2xl font-semibold leading-none tracking-tight">Password</h3>
            <p class="text-sm text-muted-foreground">Change your account password.</p>
          </div>
          <div class="p-6 space-y-4">
            <div class="space-y-2">
              <label
                  class="text-sm font-medium leading-none peer-disabled:cursor-not-allowed peer-disabled:opacity-70"
                  for="current-password"
              >
                Current Password
              </label>
              <input
                  class="flex h-10 w-full rounded-md border border-input bg-background px-3 py-2 text-sm ring-offset-background file:border-0 file:bg-transparent file:text-sm file:font-medium placeholder:text-muted-foreground focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-ring focus-visible:ring-offset-2 disabled:cursor-not-allowed disabled:opacity-50"
                  id="current-password"
                  type="password"
              />
            </div>
            <div class="space-y-2">
              <label
                  class="text-sm font-medium leading-none peer-disabled:cursor-not-allowed peer-disabled:opacity-70"
                  for="new-password"
              >
                New Password
              </label>
              <input
                  class="flex h-10 w-full rounded-md border border-input bg-background px-3 py-2 text-sm ring-offset-background file:border-0 file:bg-transparent file:text-sm file:font-medium placeholder:text-muted-foreground focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-ring focus-visible:ring-offset-2 disabled:cursor-not-allowed disabled:opacity-50"
                  id="new-password"
                  type="password"
              />
            </div>
            <div class="space-y-2">
              <label
                  class="text-sm font-medium leading-none peer-disabled:cursor-not-allowed peer-disabled:opacity-70"
                  for="confirm-password"
              >
                Confirm Password
              </label>
              <input
                  class="flex h-10 w-full rounded-md border border-input bg-background px-3 py-2 text-sm ring-offset-background file:border-0 file:bg-transparent file:text-sm file:font-medium placeholder:text-muted-foreground focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-ring focus-visible:ring-offset-2 disabled:cursor-not-allowed disabled:opacity-50"
                  id="confirm-password"
                  type="password"
              />
            </div>
          </div>
          <div class="flex items-center p-6">
            <button class="inline-flex items-center justify-center whitespace-nowrap rounded-md text-sm font-medium ring-offset-background transition-colors focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-ring focus-visible:ring-offset-2 disabled:pointer-events-none disabled:opacity-50 bg-primary text-primary-foreground hover:bg-primary/90 h-10 px-4 py-2 ml-auto">
              Change Password
            </button>
          </div>
        </div>
        <div class="rounded-lg border bg-card text-card-foreground shadow-sm" data-v0-t="card">
          <div class="flex flex-col space-y-1.5 p-6">
            <h3 class="whitespace-nowrap text-2xl font-semibold leading-none tracking-tight">Preferences</h3>
            <p class="text-sm text-muted-foreground">Customize your account preferences.</p>
          </div>
          <div class="p-6 space-y-4">
            <div class="flex items-center justify-between">
              <div class="space-y-1">
                <p class="text-sm font-medium">Dark Mode</p>
                <p class="text-sm text-gray-500 dark:text-gray-400">Switch between light and dark mode.</p>
              </div>
              <button
                  type="button"
                  role="switch"
                  aria-checked="false"
                  data-state="unchecked"
                  value="on"
                  class="peer inline-flex h-[24px] w-[44px] shrink-0 cursor-pointer items-center rounded-full border-2 border-transparent transition-colors focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-ring focus-visible:ring-offset-2 focus-visible:ring-offset-background disabled:cursor-not-allowed disabled:opacity-50 data-[state=checked]:bg-primary data-[state=unchecked]:bg-input"
                  id="dark-mode"
              >
                <span
                    data-state="unchecked"
                    class="pointer-events-none block h-5 w-5 rounded-full bg-background shadow-lg ring-0 transition-transform data-[state=checked]:translate-x-5 data-[state=unchecked]:translate-x-0"
                ></span>
              </button>
              <input
                  aria-hidden="true"
                  tabindex="-1"
                  type="checkbox"
                  value="on"
                  style="transform: translateX(-100%); position: absolute; pointer-events: none; opacity: 0; margin: 0px;"
              />
            </div>
            <div class="flex items-center justify-between">
              <div class="space-y-1">
                <p class="text-sm font-medium">Email Notifications</p>
                <p class="text-sm text-gray-500 dark:text-gray-400">
                  Receive email notifications for new activity.
                </p>
              </div>
              <button
                  type="button"
                  role="switch"
                  aria-checked="true"
                  data-state="checked"
                  value="on"
                  class="peer inline-flex h-[24px] w-[44px] shrink-0 cursor-pointer items-center rounded-full border-2 border-transparent transition-colors focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-ring focus-visible:ring-offset-2 focus-visible:ring-offset-background disabled:cursor-not-allowed disabled:opacity-50 data-[state=checked]:bg-primary data-[state=unchecked]:bg-input"
                  id="email-notifications"
              >
                <span
                    data-state="checked"
                    class="pointer-events-none block h-5 w-5 rounded-full bg-background shadow-lg ring-0 transition-transform data-[state=checked]:translate-x-5 data-[state=unchecked]:translate-x-0"
                ></span>
              </button>
              <input
                  aria-hidden="true"
                  tabindex="-1"
                  type="checkbox"
                  value="on"
                  style="transform: translateX(-100%); position: absolute; pointer-events: none; opacity: 0; margin: 0px;"
              />
            </div>
          </div>
        </div>
      </div>
</template>
<script setup lang="ts">
</script>