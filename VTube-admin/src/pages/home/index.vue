<template>
  <div class="flex flex-col gap-5 w-full h-full min-h-full">
    <div class="flex items-center">
      <h1 class="font-semibold text-lg md:text-2xl">Notes</h1>
      <button
        class="inline-flex items-center justify-center whitespace-nowrap text-sm font-medium ring-offset-background transition-colors focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-ring focus-visible:ring-offset-2 disabled:pointer-events-none disabled:opacity-50 bg-primary text-primary-foreground hover:bg-primary/90 h-9 rounded-md px-3 ml-auto"
      >
        New Note
      </button>
    </div>
    <div class="grid grid-cols-1 gap-4 md:grid-cols-2 lg:grid-cols-3">
      <div class="rounded-lg border bg-card text-card-foreground shadow-sm">
        <div class="flex flex-col space-y-1.5 p-6">
          <h3
            class="whitespace-nowrap text-2xl font-semibold leading-none tracking-tight"
          >
            Meeting Notes
          </h3>
          <p class="text-sm text-muted-foreground">
            Key takeaways from the team meeting on 2023-04-29.
          </p>
        </div>
        <div class="p-6">
          <p>
            - Discussed new product roadmap
            <br />
            - Assigned tasks for the next sprint
            <br />
            - Reviewed customer feedback
          </p>
        </div>
        <div class="flex items-center p-6">
          <div class="flex items-center justify-between">
            <div
              class="inline-flex w-fit items-center whitespace-nowrap rounded-full border px-2.5 py-0.5 font-semibold transition-colors focus:outline-none focus:ring-2 focus:ring-ring focus:ring-offset-2 text-foreground text-xs"
            >
              Meeting
            </div>
            <div class="text-xs text-gray-500 dark:text-gray-400">
              2 days ago
            </div>
          </div>
        </div>
      </div>
      <div class="rounded-lg border bg-card text-card-foreground shadow-sm">
        <div class="flex flex-col space-y-1.5 p-6">
          <h3
            class="whitespace-nowrap text-2xl font-semibold leading-none tracking-tight"
          >
            Design Brainstorm
          </h3>
          <p class="text-sm text-muted-foreground">
            Ideas and sketches from the design team meeting.
          </p>
        </div>
        <div class="p-6">
          <p>
            - Explored new color palettes
            <br />
            - Discussed UI patterns and interactions
            <br />
            - Reviewed competitor designs
          </p>
        </div>
        <div class="flex items-center p-6">
          <div class="flex items-center justify-between">
            <div
              class="inline-flex w-fit items-center whitespace-nowrap rounded-full border px-2.5 py-0.5 font-semibold transition-colors focus:outline-none focus:ring-2 focus:ring-ring focus:ring-offset-2 text-foreground text-xs"
            >
              Design
            </div>
            <div class="text-xs text-gray-500 dark:text-gray-400">
              1 week ago
            </div>
          </div>
        </div>
      </div>
      <div
        class="rounded-lg border bg-card text-card-foreground shadow-sm"
        data-v0-t="card"
      >
        <div class="flex flex-col space-y-1.5 p-6">
          <h3
            class="whitespace-nowrap text-2xl font-semibold leading-none tracking-tight"
          >
            Engineering Retrospective
          </h3>
          <p class="text-sm text-muted-foreground">
            Lessons learned and action items from the sprint review.
          </p>
        </div>
        <div class="p-6">
          <p>
            - Discussed technical debt and refactoring
            <br />
            - Reviewed test coverage and CI/CD pipeline
            <br />
            - Identified areas for improvement
          </p>
        </div>
        <div class="flex items-center p-6">
          <div class="flex items-center justify-between">
            <div
              class="inline-flex w-fit items-center whitespace-nowrap rounded-full border px-2.5 py-0.5 font-semibold transition-colors focus:outline-none focus:ring-2 focus:ring-ring focus:ring-offset-2 text-foreground text-xs"
            >
              Engineering
            </div>
            <div class="text-xs text-gray-500 dark:text-gray-400">
              3 weeks ago
            </div>
          </div>
        </div>
      </div>
    </div>
    <div class="border shadow-sm rounded-lg">
      <div class="relative w-full overflow-auto">
        <table class="w-full caption-bottom text-sm">
          <thead class="[&amp;_tr]:border-b">
            <tr
              class="border-b transition-colors hover:bg-muted/50 data-[state=selected]:bg-muted"
            >
              <th
                class="h-12 px-4 text-left align-middle font-medium text-muted-foreground [&amp;:has([role=checkbox])]:pr-0"
              >
                Title
              </th>
              <th
                class="h-12 px-4 text-left align-middle font-medium text-muted-foreground [&amp;:has([role=checkbox])]:pr-0 hidden md:table-cell"
              >
                Category
              </th>
              <th
                class="h-12 px-4 text-left align-middle font-medium text-muted-foreground [&amp;:has([role=checkbox])]:pr-0 hidden md:table-cell"
              >
                Created At
              </th>
              <th
                class="h-12 px-4 text-left align-middle font-medium text-muted-foreground [&amp;:has([role=checkbox])]:pr-0"
              >
                Actions
              </th>
            </tr>
          </thead>
          <tbody class="[&amp;_tr:last-child]:border-0">
            <tr
              class="border-b transition-colors hover:bg-muted/50 data-[state=selected]:bg-muted"
            >
              <td
                class="p-4 align-middle [&amp;:has([role=checkbox])]:pr-0 font-medium"
              >
                Product Roadmap 2023
              </td>
              <td
                class="p-4 align-middle [&amp;:has([role=checkbox])]:pr-0 hidden md:table-cell"
              >
                Planning
              </td>
              <td
                class="p-4 align-middle [&amp;:has([role=checkbox])]:pr-0 hidden md:table-cell"
              >
                2023-04-15
              </td>
              <td class="p-4 align-middle [&amp;:has([role=checkbox])]:pr-0">
                <button
                  class="inline-flex items-center justify-center whitespace-nowrap rounded-md text-sm font-medium ring-offset-background transition-colors focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-ring focus-visible:ring-offset-2 disabled:pointer-events-none disabled:opacity-50 hover:bg-accent hover:text-accent-foreground h-10 w-10"
                >
                  <svg
                    xmlns="http://www.w3.org/2000/svg"
                    width="24"
                    height="24"
                    viewBox="0 0 24 24"
                    fill="none"
                    stroke="currentColor"
                    stroke-width="2"
                    stroke-linecap="round"
                    stroke-linejoin="round"
                    class="h-4 w-4"
                  >
                    <path
                      d="M4 13.5V4a2 2 0 0 1 2-2h8.5L20 7.5V20a2 2 0 0 1-2 2h-5.5"
                    ></path>
                    <polyline points="14 2 14 8 20 8"></polyline>
                    <path
                      d="M10.42 12.61a2.1 2.1 0 1 1 2.97 2.97L7.95 21 4 22l.99-3.95 5.43-5.44Z"
                    ></path>
                  </svg>
                  <span class="sr-only">Edit</span>
                </button>
                <button
                  class="inline-flex items-center justify-center whitespace-nowrap rounded-md text-sm font-medium ring-offset-background transition-colors focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-ring focus-visible:ring-offset-2 disabled:pointer-events-none disabled:opacity-50 hover:bg-accent hover:text-accent-foreground h-10 w-10"
                >
                  <svg
                    xmlns="http://www.w3.org/2000/svg"
                    width="24"
                    height="24"
                    viewBox="0 0 24 24"
                    fill="none"
                    stroke="currentColor"
                    stroke-width="2"
                    stroke-linecap="round"
                    stroke-linejoin="round"
                    class="h-4 w-4"
                  >
                    <path d="M3 6h18"></path>
                    <path d="M19 6v14c0 1-1 2-2 2H7c-1 0-2-1-2-2V6"></path>
                    <path d="M8 6V4c0-1 1-2 2-2h4c1 0 2 1 2 2v2"></path>
                  </svg>
                  <span class="sr-only">Delete</span>
                </button>
              </td>
            </tr>
            <tr
              class="border-b transition-colors hover:bg-muted/50 data-[state=selected]:bg-muted"
            >
              <td
                class="p-4 align-middle [&amp;:has([role=checkbox])]:pr-0 font-medium"
              >
                Design System Guidelines
              </td>
              <td
                class="p-4 align-middle [&amp;:has([role=checkbox])]:pr-0 hidden md:table-cell"
              >
                Design
              </td>
              <td
                class="p-4 align-middle [&amp;:has([role=checkbox])]:pr-0 hidden md:table-cell"
              >
                2023-04-10
              </td>
              <td class="p-4 align-middle [&amp;:has([role=checkbox])]:pr-0">
                <button
                  class="inline-flex items-center justify-center whitespace-nowrap rounded-md text-sm font-medium ring-offset-background transition-colors focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-ring focus-visible:ring-offset-2 disabled:pointer-events-none disabled:opacity-50 hover:bg-accent hover:text-accent-foreground h-10 w-10"
                >
                  <svg
                    xmlns="http://www.w3.org/2000/svg"
                    width="24"
                    height="24"
                    viewBox="0 0 24 24"
                    fill="none"
                    stroke="currentColor"
                    stroke-width="2"
                    stroke-linecap="round"
                    stroke-linejoin="round"
                    class="h-4 w-4"
                  >
                    <path
                      d="M4 13.5V4a2 2 0 0 1 2-2h8.5L20 7.5V20a2 2 0 0 1-2 2h-5.5"
                    ></path>
                    <polyline points="14 2 14 8 20 8"></polyline>
                    <path
                      d="M10.42 12.61a2.1 2.1 0 1 1 2.97 2.97L7.95 21 4 22l.99-3.95 5.43-5.44Z"
                    ></path>
                  </svg>
                  <span class="sr-only">Edit</span>
                </button>
                <button
                  class="inline-flex items-center justify-center whitespace-nowrap rounded-md text-sm font-medium ring-offset-background transition-colors focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-ring focus-visible:ring-offset-2 disabled:pointer-events-none disabled:opacity-50 hover:bg-accent hover:text-accent-foreground h-10 w-10"
                >
                  <svg
                    xmlns="http://www.w3.org/2000/svg"
                    width="24"
                    height="24"
                    viewBox="0 0 24 24"
                    fill="none"
                    stroke="currentColor"
                    stroke-width="2"
                    stroke-linecap="round"
                    stroke-linejoin="round"
                    class="h-4 w-4"
                  >
                    <path d="M3 6h18"></path>
                    <path d="M19 6v14c0 1-1 2-2 2H7c-1 0-2-1-2-2V6"></path>
                    <path d="M8 6V4c0-1 1-2 2-2h4c1 0 2 1 2 2v2"></path>
                  </svg>
                  <span class="sr-only">Delete</span>
                </button>
              </td>
            </tr>
            <tr
              class="border-b transition-colors hover:bg-muted/50 data-[state=selected]:bg-muted"
            >
              <td
                class="p-4 align-middle [&amp;:has([role=checkbox])]:pr-0 font-medium"
              >
                Engineering Retrospective
              </td>
              <td
                class="p-4 align-middle [&amp;:has([role=checkbox])]:pr-0 hidden md:table-cell"
              >
                Engineering
              </td>
              <td
                class="p-4 align-middle [&amp;:has([role=checkbox])]:pr-0 hidden md:table-cell"
              >
                2023-04-05
              </td>
              <td class="p-4 align-middle [&amp;:has([role=checkbox])]:pr-0">
                <button
                  class="inline-flex items-center justify-center whitespace-nowrap rounded-md text-sm font-medium ring-offset-background transition-colors focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-ring focus-visible:ring-offset-2 disabled:pointer-events-none disabled:opacity-50 hover:bg-accent hover:text-accent-foreground h-10 w-10"
                >
                  <svg
                    xmlns="http://www.w3.org/2000/svg"
                    width="24"
                    height="24"
                    viewBox="0 0 24 24"
                    fill="none"
                    stroke="currentColor"
                    stroke-width="2"
                    stroke-linecap="round"
                    stroke-linejoin="round"
                    class="h-4 w-4"
                  >
                    <path
                      d="M4 13.5V4a2 2 0 0 1 2-2h8.5L20 7.5V20a2 2 0 0 1-2 2h-5.5"
                    ></path>
                    <polyline points="14 2 14 8 20 8"></polyline>
                    <path
                      d="M10.42 12.61a2.1 2.1 0 1 1 2.97 2.97L7.95 21 4 22l.99-3.95 5.43-5.44Z"
                    ></path>
                  </svg>
                  <span class="sr-only">Edit</span>
                </button>
                <button
                  class="inline-flex items-center justify-center whitespace-nowrap rounded-md text-sm font-medium ring-offset-background transition-colors focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-ring focus-visible:ring-offset-2 disabled:pointer-events-none disabled:opacity-50 hover:bg-accent hover:text-accent-foreground h-10 w-10"
                >
                  <svg
                    xmlns="http://www.w3.org/2000/svg"
                    width="24"
                    height="24"
                    viewBox="0 0 24 24"
                    fill="none"
                    stroke="currentColor"
                    stroke-width="2"
                    stroke-linecap="round"
                    stroke-linejoin="round"
                    class="h-4 w-4"
                  >
                    <path d="M3 6h18"></path>
                    <path d="M19 6v14c0 1-1 2-2 2H7c-1 0-2-1-2-2V6"></path>
                    <path d="M8 6V4c0-1 1-2 2-2h4c1 0 2 1 2 2v2"></path>
                  </svg>
                  <span class="sr-only">Delete</span>
                </button>
              </td>
            </tr>
            <tr
              class="border-b transition-colors hover:bg-muted/50 data-[state=selected]:bg-muted"
            >
              <td
                class="p-4 align-middle [&amp;:has([role=checkbox])]:pr-0 font-medium"
              >
                Customer Feedback Summary
              </td>
              <td
                class="p-4 align-middle [&amp;:has([role=checkbox])]:pr-0 hidden md:table-cell"
              >
                Support
              </td>
              <td
                class="p-4 align-middle [&amp;:has([role=checkbox])]:pr-0 hidden md:table-cell"
              >
                2023-03-30
              </td>
              <td class="p-4 align-middle [&amp;:has([role=checkbox])]:pr-0">
                <button
                  class="inline-flex items-center justify-center whitespace-nowrap rounded-md text-sm font-medium ring-offset-background transition-colors focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-ring focus-visible:ring-offset-2 disabled:pointer-events-none disabled:opacity-50 hover:bg-accent hover:text-accent-foreground h-10 w-10"
                >
                  <svg
                    xmlns="http://www.w3.org/2000/svg"
                    width="24"
                    height="24"
                    viewBox="0 0 24 24"
                    fill="none"
                    stroke="currentColor"
                    stroke-width="2"
                    stroke-linecap="round"
                    stroke-linejoin="round"
                    class="h-4 w-4"
                  >
                    <path
                      d="M4 13.5V4a2 2 0 0 1 2-2h8.5L20 7.5V20a2 2 0 0 1-2 2h-5.5"
                    ></path>
                    <polyline points="14 2 14 8 20 8"></polyline>
                    <path
                      d="M10.42 12.61a2.1 2.1 0 1 1 2.97 2.97L7.95 21 4 22l.99-3.95 5.43-5.44Z"
                    ></path>
                  </svg>
                  <span class="sr-only">Edit</span>
                </button>
                <button
                  class="inline-flex items-center justify-center whitespace-nowrap rounded-md text-sm font-medium ring-offset-background transition-colors focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-ring focus-visible:ring-offset-2 disabled:pointer-events-none disabled:opacity-50 hover:bg-accent hover:text-accent-foreground h-10 w-10"
                >
                  <svg
                    xmlns="http://www.w3.org/2000/svg"
                    width="24"
                    height="24"
                    viewBox="0 0 24 24"
                    fill="none"
                    stroke="currentColor"
                    stroke-width="2"
                    stroke-linecap="round"
                    stroke-linejoin="round"
                    class="h-4 w-4"
                  >
                    <path d="M3 6h18"></path>
                    <path d="M19 6v14c0 1-1 2-2 2H7c-1 0-2-1-2-2V6"></path>
                    <path d="M8 6V4c0-1 1-2 2-2h4c1 0 2 1 2 2v2"></path>
                  </svg>
                  <span class="sr-only">Delete</span>
                </button>
              </td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
    <div class="grid grid-cols-1 gap-4 md:grid-cols-2">
      <div
        class="rounded-lg border bg-card text-card-foreground shadow-sm"
        data-v0-t="card"
      >
        <div class="flex flex-col space-y-1.5 p-6">
          <h3
            class="whitespace-nowrap text-2xl font-semibold leading-none tracking-tight"
          >
            Recent Activity
          </h3>
        </div>
        <div class="p-6">
          <div class="space-y-4">
            <div class="flex items-start gap-3">
              <img
                src="https://generated.vusercontent.net/placeholder.svg"
                width="40"
                height="40"
                class="rounded-full"
                alt="Avatar"
                style="aspect-ratio: 40 / 40; object-fit: cover"
              />
              <div>
                <p class="font-medium">John Doe</p>
                <p class="text-sm text-gray-500 dark:text-gray-400">
                  Created a new note: "Design Brainstorm"
                </p>
                <p class="text-xs text-gray-500 dark:text-gray-400">
                  1 week ago
                </p>
              </div>
            </div>
            <div class="flex items-start gap-3">
              <img
                src="https://generated.vusercontent.net/placeholder.svg"
                width="40"
                height="40"
                class="rounded-full"
                alt="Avatar"
                style="aspect-ratio: 40 / 40; object-fit: cover"
              />
              <div>
                <p class="font-medium">Jane Smith</p>
                <p class="text-sm text-gray-500 dark:text-gray-400">
                  Updated the "Product Roadmap 2023" note
                </p>
                <p class="text-xs text-gray-500 dark:text-gray-400">
                  2 days ago
                </p>
              </div>
            </div>
            <div class="flex items-start gap-3">
              <img
                src="https://generated.vusercontent.net/placeholder.svg"
                width="40"
                height="40"
                class="rounded-full"
                alt="Avatar"
                style="aspect-ratio: 40 / 40; object-fit: cover"
              />
              <div>
                <p class="font-medium">Bob Johnson</p>
                <p class="text-sm text-gray-500 dark:text-gray-400">
                  Commented on the "Engineering Retrospective" note
                </p>
                <p class="text-xs text-gray-500 dark:text-gray-400">
                  3 weeks ago
                </p>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
