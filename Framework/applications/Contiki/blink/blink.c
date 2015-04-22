#include "contiki.h"
#include "dev/leds.h"

 /*---------------------------------------------------------------------------*/
/* We declare the two processes */
PROCESS(sky_shell_process, "Sky-Shell");
PROCESS(blink_process, "LED blink process");

 /* We require the processes to be started automatically */
AUTOSTART_PROCESSES(&sky_shell_process, &blink_process);
/*---------------------------------------------------------------------------*/
#define WITH_PERIODIC_DEBUG 0
#if WITH_PERIODIC_DEBUG
static struct ctimer debug_timer;
static void
periodic_debug(void *ptr)
{
  ctimer_set(&debug_timer, 20 * CLOCK_SECOND, periodic_debug, NULL);
  collect_print_stats();
}
#endif /* WITH_PERIODIC_DEBUG */
/*---------------------------------------------------------------------------*/
PROCESS_THREAD(sky_shell_process, ev, data)
{
  PROCESS_BEGIN();

#if WITH_PERIODIC_DEBUG
  ctimer_set(&debug_timer, 20 * CLOCK_SECOND, periodic_debug, NULL);
#endif /* WITH_PERIODIC_DEBUG */

  serial_shell_init();
//  shell_blink_init();
  /*  shell_file_init();
      shell_coffee_init();*/
  /*  shell_download_init();
      shell_rime_sendcmd_init();*/
  /*  shell_ps_init();*/
//  shell_reboot_init();
  shell_rime_init();
  shell_rime_netcmd_init();
  /*  shell_rime_ping_init();
  shell_rime_debug_init();
  shell_rime_debug_runicast_init();*/
  /*  shell_rime_sniff_init();*/
  shell_sky_init();
  shell_power_init();
  shell_powertrace_init();
  /*  shell_base64_init();*/
//  shell_text_init();
//  shell_time_init();
  /*  shell_checkpoint_init();*/
  /*  shell_sendtest_init();*/

//  shell_collect_view_init();

#if DEBUG_SNIFFERS
  rime_sniffer_add(&s);
#endif /* DEBUG_SNIFFERS */
  
  PROCESS_END();
}
 /*---------------------------------------------------------------------------*/
 /* Implementation of the second process */
 PROCESS_THREAD(blink_process, ev, data)
 {
     static struct etimer timer;
     static uint8_t leds_state = 0;
     PROCESS_BEGIN();

     while (1)
     {
         // we set the timer from here every time
         etimer_set(&timer, CLOCK_CONF_SECOND / 4);

         // and wait until the vent we receive is the one we're waiting for
         PROCESS_WAIT_EVENT_UNTIL(ev == PROCESS_EVENT_TIMER);
         // update the LEDs
         leds_off(0xFF);
         leds_on(leds_state);
         leds_state += 1;
     }
     PROCESS_END();
 }
